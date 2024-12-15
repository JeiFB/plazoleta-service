package com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository;

import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity,Long> {
    boolean existsByIdClientAndState(Long id, String state);
}
