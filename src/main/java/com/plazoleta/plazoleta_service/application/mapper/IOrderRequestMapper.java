package com.plazoleta.plazoleta_service.application.mapper;

import com.plazoleta.plazoleta_service.application.dtos.request.OrderRequestDto;
import com.plazoleta.plazoleta_service.domain.model.Order;
import com.plazoleta.plazoleta_service.domain.model.orders.OrderRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {
    Order toOrder(OrderRequestDto orderRequestDto);

    OrderRequestModel toOrderRequest(OrderRequestDto orderRequestDto);
}
