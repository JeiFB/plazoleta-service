package com.plazoleta.plazoleta_service.application.handler;

import com.plazoleta.plazoleta_service.application.dtos.request.RestaurantAndEmployeeRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantAndEmployeeResponseDto;
import com.plazoleta.plazoleta_service.application.mapper.IRestaurantAndEmployeeRequestMapper;
import com.plazoleta.plazoleta_service.application.mapper.IRestaurantAndEmployeeResponseMapper;
import com.plazoleta.plazoleta_service.domain.api.IRestaurantAndEmployeeServicePort;
import com.plazoleta.plazoleta_service.domain.model.RestaurantAndEmployee;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantAndEmployeeHandler implements IRestaurantAndEmployeeHandler {

    private final IRestaurantAndEmployeeRequestMapper restaurantAndEmployeeRequestMapper;
    private final IRestaurantAndEmployeeResponseMapper restaurantAndEmployeeResponseMapper;
    private final IRestaurantAndEmployeeServicePort restaurantAndEmployeeServicePort;
    @Override
    public void saveRestaurantEmployee(RestaurantAndEmployeeRequestDto restaurantEmployeeRequestDto) {
        RestaurantAndEmployee restaurantAndEmployee = restaurantAndEmployeeRequestMapper.toRestaurantEmployeeModel(restaurantEmployeeRequestDto);
        restaurantAndEmployeeServicePort.saveRestaurantEmployee(restaurantAndEmployee);
    }

    @Override
    public List<RestaurantAndEmployeeResponseDto> getAllRestaurantEmployees() {
        return restaurantAndEmployeeResponseMapper.toResponseList(restaurantAndEmployeeServicePort.getAllRestaurantEmployees());
    }
}
