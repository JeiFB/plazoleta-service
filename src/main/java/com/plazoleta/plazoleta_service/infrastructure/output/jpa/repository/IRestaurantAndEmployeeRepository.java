package com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository;

import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.RestaurantAndEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantAndEmployeeRepository extends JpaRepository<RestaurantAndEmployeeEntity, Long> {
    Optional<RestaurantAndEmployeeEntity> findByEmployeeId(Long idEmployee);
}
