package com.plazoleta.plazoleta_service.domain.api;

import com.plazoleta.plazoleta_service.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurantById(Long id);

    Restaurant getRestaurantByIdOwner(Long idOwner);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getRestaurantWithPagination(Integer page, Integer size);

    void deleteRestaurantById(Long id);

}
