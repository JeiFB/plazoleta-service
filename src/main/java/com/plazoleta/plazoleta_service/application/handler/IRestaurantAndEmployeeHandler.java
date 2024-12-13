package com.plazoleta.plazoleta_service.application.handler;

import com.plazoleta.plazoleta_service.application.dtos.request.RestaurantAndEmployeeRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantAndEmployeeResponseDto;

import java.util.List;

public interface IRestaurantAndEmployeeHandler {
    void saveRestaurantEmployee(RestaurantAndEmployeeRequestDto restaurantEmployeeRequestDto);

    List<RestaurantAndEmployeeResponseDto> getAllRestaurantEmployees();
}
