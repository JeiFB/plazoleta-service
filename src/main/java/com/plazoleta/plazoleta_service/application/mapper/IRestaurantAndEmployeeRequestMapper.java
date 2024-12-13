package com.plazoleta.plazoleta_service.application.mapper;

import com.plazoleta.plazoleta_service.application.dtos.request.RestaurantAndEmployeeRequestDto;
import com.plazoleta.plazoleta_service.domain.model.RestaurantAndEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantAndEmployeeRequestMapper {
    RestaurantAndEmployee toRestaurantEmployeeModel(RestaurantAndEmployeeRequestDto restaurantEmployeeRequestDto);
}
