package com.plazoleta.plazoleta_service.domain.usecase;

import com.plazoleta.plazoleta_service.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta_service.domain.model.*;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderDishRequestModel;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderDishResponseModel;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderRequestModel;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderResponseModel;
import com.plazoleta.plazoleta_service.domain.spi.bearertoken.IToken;
import com.plazoleta.plazoleta_service.domain.spi.feignClients.ITwilioFeignClientPort;
import com.plazoleta.plazoleta_service.domain.spi.feignClients.IUserFeignClientPort;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IDishPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IOrderPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IRestaurantAndEmployeePersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private  final IOrderPersistencePort orderPersistencePort;
    private  final IToken token;
    private  final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private  final IRestaurantAndEmployeePersistencePort restaurantAndEmployeePersistencePort;
    private  final IUserFeignClientPort userFeignClientPort;
    private final ITwilioFeignClientPort twilioFeignClientPort;

    @Override
    public void saveOrder(OrderRequestModel orderRequestModel) {
        Date date = new Date();
        String bearerToken = token.getBearerToken();
        if (bearerToken == null) throw new IllegalArgumentException("no hay datos de persona");
        Long idClientAuth = token.getUserAuthenticationId(bearerToken);

        List<String> state = List.of("PENDIENTE", "EN_PREPARACION", "LISTO");

        if(orderPersistencePort.existsByIdClientAndState(idClientAuth, state.get(0))||
                orderPersistencePort.existsByIdClientAndState(idClientAuth, state.get(1)) ||
                orderPersistencePort.existsByIdClientAndState(idClientAuth, state.get(2))) throw  new IllegalArgumentException("cliente tiene orden");

        Long idRestaurant = orderRequestModel.getRestaurantId();

        Restaurant restaurant = restaurantPersistencePort.getRestaurantById(idRestaurant);


        if(restaurant == null) throw new IllegalArgumentException("restaurante no existe");
        Order orderModel = new Order(null, idClientAuth, date, "PENDIENTE", null, restaurant);

        List<OrderDishRequestModel> orderDishes = orderRequestModel.getDishes();
        if(orderDishes.isEmpty()){
            throw new IllegalArgumentException("no hay ordenes");
        }
        for(int i=0; i<orderDishes.size(); i++){
            Dish dish = dishPersistencePort.getDishById(orderDishes.get(i).getIdDish());
            if (dish == null) throw new IllegalArgumentException("plato no existe");
            if (dish.getRestaurantId().getId() != orderModel.getRestaurant().getId()) throw new IllegalArgumentException("pedido no es de ese restaurante");
            if(!dish.getActive()) throw new IllegalArgumentException("este plato no esta disponible");
        }
        Order order = orderPersistencePort.saveOrder(orderModel);

        List<OrderDish> orderDishesList = new ArrayList<>();
        for (int i=0; i<orderDishes.size();i++){
            Dish dishModel= dishPersistencePort.getDishById(orderDishes.get(i).getIdDish());
            OrderDish orderDish = new OrderDish(null, order,dishModel, String.valueOf(orderDishes.get(i).getAmount()));
            orderDishesList.add(orderDish);
        }

        orderPersistencePort.saveOrderDish(orderDishesList);
    }

    @Override
    public List<OrderResponseModel> getAllOrderWithPagination(Integer page, Integer size, String state) {
        String bearerToken = token.getBearerToken();
        if(bearerToken==null) throw new IllegalArgumentException("usuaro invalido");
        Long idEmployeeAuth = token.getUserAuthenticationId(bearerToken);
        RestaurantAndEmployee restaurantAndEmployee = restaurantAndEmployeePersistencePort.findByEmployeeId(idEmployeeAuth);

        List<OrderResponseModel> listaPedidosResponse = new ArrayList<>();
        Long restauranteId = restaurantAndEmployee.getRestaurantId();
        List<Order> pedidos = orderPersistencePort.getAllOrderWithPagination(page, size,restauranteId ,state);

        for (int i=0; i<pedidos.size();i++){
            OrderResponseModel orderResponseModel = new OrderResponseModel();
            orderResponseModel.setId(pedidos.get(i).getId());
            orderResponseModel.setIdClient(pedidos.get(i).getIdClient());
            if(pedidos.get(i).getChef()==null) orderResponseModel.setIdChef(null);
            else orderResponseModel.setIdChef(pedidos.get(i).getChef().getId());
            orderResponseModel.setDate(pedidos.get(i).getDate());
            orderResponseModel.setOrderDishes(new ArrayList<>());

            List<OrderDish>  pedidoPlatos = orderPersistencePort.getAllOrdersByOrder(pedidos.get(i).getId());
            for (int k=0; k<pedidoPlatos.size(); k++){
                Dish dishModel= dishPersistencePort.getDishById(pedidoPlatos.get(k).getDish().getId());
                OrderDishResponseModel orderDishResponseModel = new OrderDishResponseModel();
                orderDishResponseModel.setId(dishModel.getId());
                orderDishResponseModel.setName(dishModel.getName());
                orderDishResponseModel.setPrice(dishModel.getPrice());
                orderDishResponseModel.setDescription(dishModel.getDescription());
                orderDishResponseModel.setUrlImg(dishModel.getImageUrl());
                orderDishResponseModel.setCategoryId(dishModel.getCategory());
                orderDishResponseModel.setAmount(pedidoPlatos.get(k).getAmount());

                orderResponseModel.getOrderDishes().add(orderDishResponseModel);
            }
            listaPedidosResponse.add(orderResponseModel);
        }
        return listaPedidosResponse;
    }

    @Override
    public void takeOrderAndUpdateStatus(Long idOrder, String state) {
        if(!state.equals("EN_PREPARACION")) throw new IllegalArgumentException("Ya esta en preparacion") ;
        if(Boolean.FALSE.equals(orderPersistencePort.existsByIdAndState(idOrder, "PENDIENTE"))) throw new IllegalArgumentException("Este pedido no existe");

        String bearerToken = token.getBearerToken();
        if(bearerToken==null) throw new IllegalArgumentException("NO existe propietario");
        Long idEmployeeAuth = token.getUserAuthenticationId(bearerToken);
        RestaurantAndEmployee restaurantAndEmployee= restaurantAndEmployeePersistencePort.findByEmployeeId(idEmployeeAuth);
        if(restaurantAndEmployee==null) throw new IllegalArgumentException("no es empleado de este restaurante");
        Order orderModel= orderPersistencePort.getOrderById(idOrder);
        if(orderModel==null) throw new IllegalArgumentException("no hay orden");

        Long idRestaurantEmployeeAuth = restaurantAndEmployee.getRestaurantId();
        Long idRestaurantOrder = orderModel.getRestaurant().getId();

        if(idRestaurantEmployeeAuth!=idRestaurantOrder) throw new IllegalArgumentException("este pedido no es de tu restautante");

        orderModel.setChef(restaurantAndEmployee);
        orderModel.setState(state);

        orderPersistencePort.saveOrder(orderModel);
    }

    @Override
    public void updateAndNotifyOrderReady(Long idOrder) {
        if(Boolean.FALSE.equals(orderPersistencePort.existsByIdAndState(idOrder, "EN_PREPARACION"))) throw new IllegalArgumentException("tiene que estar listo");
        String bearerToken = token.getBearerToken();
        if(bearerToken==null) throw new IllegalArgumentException("no existe");
        Long idEmployeeAuth = token.getUserAuthenticationId(bearerToken);
        RestaurantAndEmployee restaurantEmployeeModel= restaurantAndEmployeePersistencePort.findByEmployeeId(idEmployeeAuth);
        if(restaurantEmployeeModel==null) throw new IllegalArgumentException("restaurant no ");
        Order orderModel= orderPersistencePort.getOrderById(idOrder);
        if(orderModel==null) throw new IllegalArgumentException("Orden no existe");

        Long idRestaurantEmployeeAuth = restaurantEmployeeModel.getRestaurantId();
        Long idRestaurantOrder = orderModel.getRestaurant().getId();

        if(idRestaurantEmployeeAuth!=idRestaurantOrder) throw new IllegalArgumentException("los restaurantes no coinciden");

        orderModel.setState("LISTO");
        orderPersistencePort.saveOrder(orderModel);

        User userModel = userFeignClientPort.getUserById(orderModel.getIdClient());
        String nombreCliente = userModel.getName();
        String pin = validatePin(userModel);

        String mensaje = "Buen día, señor(a) " + nombreCliente.toUpperCase() + ", su pedido ya está listo para recoger.\nRecuerda mostrar el siguiente pin " + pin + " para poder entregar tu pedido.";
        String numeroCelular = "+573154416131";
        // No coloco el celular del cliente, ya que Twilio solo deja enviar mensajes al celular de la cuenta creada
        // String numeroCel = userModel.getCelular();

        SmsMessage smsMessageModel = new SmsMessage(numeroCelular, mensaje);

        twilioFeignClientPort.sendSmsMessage(smsMessageModel);
        System.out.println("PASO POR ACAaaaaaaaaaaaaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA X2");
    }

    @Override
    public void deliverOrder(Long idOrder, String pin) {
        if(Boolean.FALSE.equals(orderPersistencePort.existsByIdAndState(idOrder, "LISTO"))) throw new IllegalArgumentException("estado incorrecto");
        String bearerToken = token.getBearerToken();
        if(bearerToken==null) throw new IllegalArgumentException("no existe user");
        Long idEmployeeAuth = token.getUserAuthenticationId(bearerToken);
        RestaurantAndEmployee restaurantEmployeeModel= restaurantAndEmployeePersistencePort.findByEmployeeId(idEmployeeAuth);
        if(restaurantEmployeeModel==null) throw new IllegalArgumentException("no existe usuario y empleado");
        Order orderModel= orderPersistencePort.getOrderById(idOrder);
        if(orderModel==null) throw new IllegalArgumentException("no existe orden");

        Long idRestaurantEmployeeAuth = restaurantEmployeeModel.getRestaurantId();
        Long idRestaurantOrder = orderModel.getRestaurant().getId();

        if(idRestaurantEmployeeAuth!=idRestaurantOrder) throw new IllegalArgumentException("no coincide restaurante y empleado");

        User userModel = userFeignClientPort.getUserById(orderModel.getIdClient());
        String pin2 = validatePin(userModel);

        if(!(validatePin(userModel)).equals(pin) && !pin.equals(0)) throw new IllegalArgumentException("pin incorrecto");


        orderModel.setState("ENTREGADO");
        orderPersistencePort.saveOrder(orderModel);
    }

    public String validatePin(User user){
        String pinDocument = user.getDocument();
        String pinName = user.getName();
        String pinLastName = user.getLastName();
        String pin = pinName.substring(pinName.length()-2)+pinDocument.substring(pinDocument.length()-4)+pinLastName.substring(pinLastName.length()-2);
        return  pin;
    }


}
