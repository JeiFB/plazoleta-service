package com.plazoleta.plazoleta_service.infrastructure.output.jpa.adapter;

import com.plazoleta.plazoleta_service.domain.model.Dish;
import com.plazoleta.plazoleta_service.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.DishEntity;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private  final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;


    @Override
    public Dish getDishById(Long id) {
        Optional<DishEntity> dishEntityOptional = dishRepository.findById(id);
        DishEntity dishEntity = dishEntityOptional.orElse(null);
        return dishEntityMapper.toDish(dishEntity);
    }

    @Override
    public List<Dish> getAllDishes() {
        List<DishEntity> dishEntityList = dishRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
        return dishEntityMapper.toDishList(dishEntityList);
    }

    @Override
    public void createDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);

        dishRepository.save(dishEntity);
    }

    @Override
    public List<Dish> dishesAllByRestaurantId(Long idRestaurant, Integer page, Integer size) {
        return List.of();
    }

}
