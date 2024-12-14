package com.plazoleta.plazoleta_service.application.mapper;

import com.plazoleta.plazoleta_service.application.dtos.request.DishRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.request.DishUpdateRequestDto;
import com.plazoleta.plazoleta_service.domain.model.Dish;
import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IDishRequestMapper {

    @Mapping(target = "category.id", source = "category")
    @Mapping(target = "restaurantId", source = "restaurantId")
    Dish toDish(DishRequestDto dishRequestDto);

    Dish toDishUpdate(DishUpdateRequestDto dishUpdateRequestDto);


    default Restaurant map(Long restaurantId) {
        if (restaurantId == null) {
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        return restaurant;
    }
}


