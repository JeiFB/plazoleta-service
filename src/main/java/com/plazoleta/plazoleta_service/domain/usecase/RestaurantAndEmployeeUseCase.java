package com.plazoleta.plazoleta_service.domain.usecase;

import com.plazoleta.plazoleta_service.domain.api.IRestaurantAndEmployeeServicePort;
import com.plazoleta.plazoleta_service.domain.model.RestaurantAndEmployee;
import com.plazoleta.plazoleta_service.domain.spi.IRestaurantAndEmployeePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class RestaurantAndEmployeeUseCase implements IRestaurantAndEmployeeServicePort {

    private final IRestaurantAndEmployeePersistencePort restaurantAndEmployeePersistencePort;
    @Override
    public void saveRestaurantEmployee(RestaurantAndEmployee RestaurantEmployee) {
        restaurantAndEmployeePersistencePort.saveRestaurantEmployee(RestaurantEmployee);
    }

    @Override
    public List<RestaurantAndEmployee> getAllRestaurantEmployees() {
        return restaurantAndEmployeePersistencePort.getAllRestaurantEmployees();

    }
}
