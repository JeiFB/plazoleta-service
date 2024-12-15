package com.plazoleta.plazoleta_service.domain.api;

import com.plazoleta.plazoleta_service.domain.model.orders.OrderRequestModel;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderResponseModel;

import java.util.List;

public interface IOrderServicePort {
    void saveOrder(OrderRequestModel order);
    List<OrderResponseModel> getAllOrderWithPagination(Integer page, Integer size, String state);
}
