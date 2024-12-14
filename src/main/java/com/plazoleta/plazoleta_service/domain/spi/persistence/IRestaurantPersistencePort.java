package com.plazoleta.plazoleta_service.domain.spi.persistence;

import com.plazoleta.plazoleta_service.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(Long id);
    Restaurant getRestaurantByIdOwner(Long idOwner);
    List<Restaurant> getAllRestaurant();
    List<Restaurant> getRestaurantsWithPagination(Integer page, Integer size);
    void deleteRestaurantById(Long id);
    Boolean existsByNit(String nit);
}


