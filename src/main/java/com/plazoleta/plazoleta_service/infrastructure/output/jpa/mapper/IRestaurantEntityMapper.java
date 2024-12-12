package com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper;


import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);
}
