package com.plazoleta.plazoleta_service.domain.spi;

import com.plazoleta.plazoleta_service.domain.model.RestaurantAndEmployee;

import java.util.List;

public interface IRestaurantAndEmployeePersistencePort {

    RestaurantAndEmployee saveRestaurantEmployee(RestaurantAndEmployee restaurantEmployee);

    List<RestaurantAndEmployee> getAllRestaurantEmployees();

    RestaurantAndEmployee findByEmployeeId(Long idEmployee);
}
