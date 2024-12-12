package com.plazoleta.plazoleta_service.application.handler;

import com.plazoleta.plazoleta_service.application.dtos.request.RestaurantRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantResponseDto;
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

    @Override
    public void saveRestaurantInRestaurants(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);

        restaurantServicePort.saveRestaurant(restaurant);

    }

    @Override
    public RestaurantResponseDto getRestaurantById(Long id) {
        RestaurantResponseDto restaurantResponseDto = restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurantById(id));
        return restaurantResponseDto;
    }

    @Override
    public RestaurantResponseDto getRestaurantByIdOwner(Long idOwner) {
        RestaurantResponseDto restaurantResponseDto = restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurantByIdOwner(idOwner));
        return restaurantResponseDto;
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantServicePort.deleteRestaurantById(id);
    }
}
