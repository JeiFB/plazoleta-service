package com.plazoleta.plazoleta_service.application.mapper;

import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantAndEmployeeResponseDto;
import com.plazoleta.plazoleta_service.domain.model.RestaurantAndEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantAndEmployeeResponseMapper {
    RestaurantAndEmployeeResponseDto toResponse(RestaurantAndEmployee restaurantEmployeeModel);

    List<RestaurantAndEmployeeResponseDto> toResponseList(List<RestaurantAndEmployee> restaurantEmployeeModelList);

}
