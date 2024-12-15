package com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository;

import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity,Long> {
    Page<OrderEntity> findByRestaurantIdAndState(Long id, String state, Pageable pageable);
    boolean existsByIdClientAndState(Long id, String state);
}
