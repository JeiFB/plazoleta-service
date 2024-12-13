package com.plazoleta.plazoleta_service.application.mapper;

import com.plazoleta.plazoleta_service.application.dtos.request.RestaurantRequestDto;
import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantRequestMapper {

    Restaurant toRestaurant(RestaurantRequestDto restaurantRequestDto);

}
