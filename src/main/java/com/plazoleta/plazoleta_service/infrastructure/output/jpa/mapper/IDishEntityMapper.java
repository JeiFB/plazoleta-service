package com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper;


import com.plazoleta.plazoleta_service.domain.model.Dish;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IDishEntityMapper {

    @Mapping(target = "category.id", source = "category.id")
    DishEntity toEntity(Dish dish);

    @Mapping(target = "category.id", source = "category.id")
    Dish toDish(DishEntity dishEntity);


    List<Dish> toDishList(List<DishEntity> dishEntityList);
}
