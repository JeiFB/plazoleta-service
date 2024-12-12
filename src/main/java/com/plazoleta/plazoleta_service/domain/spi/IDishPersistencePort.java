package com.plazoleta.plazoleta_service.domain.spi;

import com.plazoleta.plazoleta_service.domain.model.Dish;

import java.util.List;

public interface IDishPersistencePort {
    Dish getDishById(Long id);
    List<Dish> getAllDishes();
    void createDish(Dish dish);
    List<Dish> dishesAllByRestaurantId(Long idRestaurant, Integer page, Integer size);
}
