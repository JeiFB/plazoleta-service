package com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper;


import com.plazoleta.plazoleta_service.domain.model.Order;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderEntityMapper {
    OrderEntity toEntity(Order order);
    Order toOrder(OrderEntity orderEntity);
}
