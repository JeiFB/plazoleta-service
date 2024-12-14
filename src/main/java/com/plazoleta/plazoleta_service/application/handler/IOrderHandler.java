package com.plazoleta.plazoleta_service.application.handler;

import com.plazoleta.plazoleta_service.application.dtos.request.OrderRequestDto;

public interface IOrderHandler {
    void saveOrder(OrderRequestDto orderRequestDto);
}
