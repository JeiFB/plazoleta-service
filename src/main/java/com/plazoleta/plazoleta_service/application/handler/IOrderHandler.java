package com.plazoleta.plazoleta_service.application.handler;

import com.plazoleta.plazoleta_service.application.dtos.request.OrderRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.OrderResponseDto;

import java.util.List;

public interface IOrderHandler {
    void saveOrder(OrderRequestDto orderRequestDto);
    List<OrderResponseDto> getAllOrdersWithPagination(Integer page, Integer size, String state);
    void takeOrderAndUpdateStatus(Long idOrder, String state);
}
