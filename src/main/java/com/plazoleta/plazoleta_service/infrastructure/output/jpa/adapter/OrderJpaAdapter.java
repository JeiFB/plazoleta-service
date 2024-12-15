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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Order> getAllOrderWithPagination(Integer page, Integer size, Long restaurantId, String state) {
        PageRequest pegeable = PageRequest.of(page,size);
        Page<OrderEntity> orderEntityPage = orderRepository.findByRestaurantIdAndState(restaurantId, state,pegeable);
        if(orderEntityPage.isEmpty()){
            throw new IllegalArgumentException("no hay ordenes");
        }
        return orderEntityPage.stream().map(orderEntityMapper::toOrder).collect(Collectors.toList());
    }

    @Override
    public List<OrderDish> getAllOrdersByOrder(Long orderId) {
        List<OrderDishEntity> orderDishEntities = orderDishRepository.findByOrderId(orderId);
        if(orderDishEntities.isEmpty()) throw  new IllegalArgumentException("no hay pedidos");
        return orderDishEntityMapper.toOrderDishModelList(orderDishEntities);
    }
}
