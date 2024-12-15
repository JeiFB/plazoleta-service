package com.plazoleta.plazoleta_service.domain.usecase;

import com.plazoleta.plazoleta_service.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta_service.domain.model.*;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderDishRequestModel;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderDishResponseModel;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderRequestModel;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderResponseModel;
import com.plazoleta.plazoleta_service.domain.spi.bearertoken.IToken;
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
}
