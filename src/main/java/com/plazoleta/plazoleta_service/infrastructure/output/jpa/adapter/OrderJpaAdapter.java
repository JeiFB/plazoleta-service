package com.plazoleta.plazoleta_service.infrastructure.output.jpa.adapter;

import com.plazoleta.plazoleta_service.domain.model.Order;
import com.plazoleta.plazoleta_service.domain.model.OrderDish;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IOrderPersistencePort;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.OrderDishEntity;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.OrderEntity;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IOrderDishEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IOrderDishRepository;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishRepository orderDishRepository;
    private  final IOrderDishEntityMapper orderDishEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderRepository.save(orderEntityMapper.toEntity(order));
        return orderEntityMapper.toOrder(orderEntity);
    }

    @Override
    public void saveOrderDish(List<OrderDish> orderDishes) {
        List<OrderDishEntity> orderDishEntities = new ArrayList<>();
        for (int i=0; i<orderDishes.size();i++){
            orderDishEntities.add(orderDishEntityMapper.toEntity(orderDishes.get(i)));
        }
        orderDishRepository.saveAll(orderDishEntities);
    }

    @Override
    public boolean existsByIdClientAndState(Long id, String state) {
        return orderRepository.existsByIdClientAndState(id, state);
    }
}
