package com.plazoleta.plazoleta_service.domain.usecase;

import com.plazoleta.plazoleta_service.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import com.plazoleta.plazoleta_service.domain.model.User;
import com.plazoleta.plazoleta_service.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.feignClients.IUserFeignClientPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {

   private final IRestaurantPersistencePort restaurantPersistencePort;
   private final IUserFeignClientPort userFeignClientPort;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        boolean userExist = userFeignClientPort.existUserById(restaurant.getIdOwner());
        if(!userExist) throw  new IllegalArgumentException();
        User user = userFeignClientPort.getUserById(restaurant.getIdOwner());
        restaurantPersistencePort.saveRestaurant(restaurant);
        if (user.getRol().getId() != 4) throw  new IllegalArgumentException();

        Restaurant restaurante = restaurantPersistencePort.getRestaurantByIdOwner(user.getId());
        if (restaurante != null) throw  new IllegalArgumentException();
        restaurantPersistencePort.saveRestaurant(restaurante);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantPersistencePort.getRestaurantById(id);
    }

    @Override
    public Restaurant getRestaurantByIdOwner(Long idOwner) {

        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }

    @Override
    public List<Restaurant> getRestaurantWithPagination(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public void deleteRestaurantById(Long id) {

    }


}
