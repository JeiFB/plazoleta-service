package com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository;

import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
}
