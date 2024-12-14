package com.plazoleta.plazoleta_service.application.handler.impl;

import com.plazoleta.plazoleta_service.application.dtos.request.RestaurantRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantPaginationResponseDto;
import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantResponseDto;
import com.plazoleta.plazoleta_service.application.handler.IRestaurantHandler;
import com.plazoleta.plazoleta_service.application.mapper.IRestaurantPaginationResponseMapper;
import com.plazoleta.plazoleta_service.application.mapper.IRestaurantRequestMapper;
import com.plazoleta.plazoleta_service.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.plazoleta_service.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IRestaurantPaginationResponseMapper restaurantPaginationResponseMapper;

    @Override
    public void saveRestaurantInRestaurants(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurant);

    }

    @Override
    public RestaurantResponseDto getRestaurantById(Long id) {
        return restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurantById(id));
    }

    @Override
    public RestaurantResponseDto getRestaurantByIdOwner(Long idOwner) {

        return restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurantByIdOwner(idOwner));
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public List<RestaurantPaginationResponseDto> getRestaurantsWithPagination(Integer page, Integer size) {
        return restaurantPaginationResponseMapper.toResponseListPagination(restaurantServicePort.getRestaurantWithPagination(page,size));
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantServicePort.deleteRestaurantById(id);
    }
}
