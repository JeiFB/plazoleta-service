package com.plazoleta.plazoleta_service.application.mapper;


import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantResponseDto;
import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    RestaurantResponseDto toResponse(Restaurant restaurant);

    List<RestaurantResponseDto> toResponseList(List<Restaurant> restaurantList);
}
