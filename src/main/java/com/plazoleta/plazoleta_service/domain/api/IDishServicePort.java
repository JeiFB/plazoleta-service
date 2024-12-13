package com.plazoleta.plazoleta_service.domain.api;

import com.plazoleta.plazoleta_service.domain.model.Dish;

import java.util.List;

public interface IDishServicePort {

    Dish getDishById(Long id);
    List<Dish> getAllDishes();
    void createDish(Dish dish);
    void updateDish(Long id, Dish dish);
    List<Dish> dishesAllByRestaurantById(Long idRestaurant, Integer page, Integer size);


}
