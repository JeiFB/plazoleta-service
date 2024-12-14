package com.plazoleta.plazoleta_service.application.handler;

import com.plazoleta.plazoleta_service.application.dtos.request.RestaurantRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantPaginationResponseDto;
import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {
    void  saveRestaurantInRestaurants(RestaurantRequestDto restaurantRequestDto);
    RestaurantResponseDto getRestaurantById(Long id);
    RestaurantResponseDto getRestaurantByIdOwner(Long idOwner);

    List<RestaurantResponseDto> getAllRestaurants();

    List<RestaurantPaginationResponseDto> getRestaurantsWithPagination(Integer page, Integer size);

    void deleteRestaurantById(Long id);

}
