package com.plazoleta.plazoleta_service.domain.api;

import com.plazoleta.plazoleta_service.domain.model.RestaurantAndEmployee;

import java.util.List;

public interface IRestaurantAndEmployeeServicePort {
    void saveRestaurantEmployee(RestaurantAndEmployee employee);

    List<RestaurantAndEmployee> getAllRestaurantEmployees();
}
