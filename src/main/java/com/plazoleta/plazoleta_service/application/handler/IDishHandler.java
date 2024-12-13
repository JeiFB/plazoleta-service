package com.plazoleta.plazoleta_service.application.handler;

import com.plazoleta.plazoleta_service.application.dtos.request.DishRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.request.DishUpdateRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {


    DishResponseDto getDishById(Long id);
    List<DishResponseDto> getAllDishes();

    void updateDish(Long id, DishUpdateRequestDto dishUpdateRequestDto);
    void createDishInDishes(DishRequestDto dishRequestDto);

}