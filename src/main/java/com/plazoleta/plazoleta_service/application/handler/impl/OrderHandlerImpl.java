package com.plazoleta.plazoleta_service.application.handler.impl;

import com.plazoleta.plazoleta_service.application.dtos.request.OrderRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.OrderResponseDto;
import com.plazoleta.plazoleta_service.application.handler.IOrderHandler;
import com.plazoleta.plazoleta_service.application.mapper.IOrderRequestMapper;
import com.plazoleta.plazoleta_service.application.mapper.IOrderResponseMapper;
import com.plazoleta.plazoleta_service.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderRequestModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandlerImpl implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;
    @Override
    public void saveOrder(OrderRequestDto orderRequestDto) {
        OrderRequestModel orderRequestModel = orderRequestMapper.toOrderRequest(orderRequestDto);
        orderServicePort.saveOrder(orderRequestModel);
    }

    @Override
    public List<OrderResponseDto> getAllOrdersWithPagination(Integer page, Integer size, String state) {
        return orderResponseMapper.toOrderResponseList(orderServicePort.getAllOrderWithPagination(page,size,state));
    }
}
