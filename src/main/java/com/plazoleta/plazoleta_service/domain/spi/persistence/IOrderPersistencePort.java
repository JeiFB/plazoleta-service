package com.plazoleta.plazoleta_service.domain.spi.persistence;

import com.plazoleta.plazoleta_service.domain.model.Order;
import com.plazoleta.plazoleta_service.domain.model.OrderDish;

import java.util.List;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
    void saveOrderDish(List<OrderDish> orderDishes);
    boolean existsByIdClientAndState(Long id, String state);
    List<Order> getAllOrderWithPagination(Integer page, Integer size, Long restaurantId, String state);
    List<OrderDish> getAllOrdersByOrder(Long orderId);
    Order getOrderById(Long id);
    Boolean existsByIdAndState(Long id, String state);

}
