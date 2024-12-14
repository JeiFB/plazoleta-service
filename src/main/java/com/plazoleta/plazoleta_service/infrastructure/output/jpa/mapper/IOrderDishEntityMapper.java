package com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper;

import com.plazoleta.plazoleta_service.domain.model.OrderDish;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderDishEntityMapper {
    OrderDishEntity toEntity(OrderDish orderDish);
    OrderDish toOrderModel(OrderDishEntity orderDishEntity);

    List<OrderDish> toOrderDishModelList(List<OrderDishEntity> orderDishEntities);
}
