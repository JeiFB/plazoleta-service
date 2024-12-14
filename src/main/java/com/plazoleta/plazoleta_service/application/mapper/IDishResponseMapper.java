package com.plazoleta.plazoleta_service.application.mapper;


import com.plazoleta.plazoleta_service.application.dtos.response.DishResponseDto;
import com.plazoleta.plazoleta_service.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    DishResponseDto toDishDto(Dish dish);
    List<DishResponseDto> toDishList(List<Dish> dishes);
    List<DishResponseDto> toResponseList(List<Dish> dishes);
}
