package com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper;


import com.plazoleta.plazoleta_service.domain.model.RestaurantAndEmployee;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.RestaurantAndEmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantAndEmployeeEntityMapper {
    RestaurantAndEmployeeEntity toEntity(RestaurantAndEmployee restaurantEmployee);

    RestaurantAndEmployee toRestaurantEmployeeModel(RestaurantAndEmployeeEntity restaurantEmployeeEntity);

    List<RestaurantAndEmployee> toRestaurantEmployeeModelList(List<RestaurantAndEmployeeEntity> restaurantEmployeeEntityList);
}
