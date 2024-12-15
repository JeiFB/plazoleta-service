package com.plazoleta.plazoleta_service.domain.usecase;

import com.plazoleta.plazoleta_service.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta_service.domain.model.Dish;
import com.plazoleta.plazoleta_service.domain.model.Order;
import com.plazoleta.plazoleta_service.domain.model.OrderDish;
import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderDishRequestModel;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderRequestModel;
import com.plazoleta.plazoleta_service.domain.spi.bearertoken.IToken;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IDishPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IOrderPersistencePort;
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
}
