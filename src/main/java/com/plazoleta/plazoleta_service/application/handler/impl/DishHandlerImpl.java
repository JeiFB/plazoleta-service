package com.plazoleta.plazoleta_service.application.handler.impl;

import com.plazoleta.plazoleta_service.application.dtos.request.DishRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.request.DishUpdateRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.DishResponseDto;
import com.plazoleta.plazoleta_service.application.handler.IDishHandler;
import com.plazoleta.plazoleta_service.application.mapper.IDishRequestMapper;
import com.plazoleta.plazoleta_service.application.mapper.IDishResponseMapper;
import com.plazoleta.plazoleta_service.domain.api.IDishServicePort;
import com.plazoleta.plazoleta_service.domain.model.Dish;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DishHandlerImpl implements IDishHandler {


    private final IDishServicePort dishServicePort;
    private  final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public DishResponseDto getDishById(Long id) {
        return dishResponseMapper.toDishDto(dishServicePort.getDishById(id));
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        List<Dish> dishes = dishServicePort.getAllDishes();
        return dishResponseMapper.toDishList(dishes);
    }

    @Override
    public List<DishResponseDto> dishesAllByRestaurantById(Long restaurantId, Integer page, Integer size) {
        List<Dish> dishes = dishServicePort.dishesAllByRestaurantById(restaurantId,page, size);
        if(dishes.isEmpty()) throw  new IllegalArgumentException("no hay datos");
        return dishResponseMapper.toDishList(dishes);
    }

    @Override
    public void updateDish(Long id, DishUpdateRequestDto dishUpdateRequestDto) {
        Dish dish = dishRequestMapper.toDishUpdate(dishUpdateRequestDto);
        dishServicePort.updateDish(id, dish);
    }

    @Override
    public void createDishInDishes(DishRequestDto dishRequestDto) {
        Dish dish = dishRequestMapper.toDish(dishRequestDto);

        dishServicePort.createDish(dish);
    }

    @Override
    public void updateEnableDisableDish(Long idDish, Long flag) {
        dishServicePort.updateEnableDisableDish(idDish, flag);
    }
}
