package com.plazoleta.plazoleta_service.domain.api;

import com.plazoleta.plazoleta_service.domain.model.orders.OrderRequestModel;

public interface IOrderServicePort {
    void saveOrder(OrderRequestModel order);
}
