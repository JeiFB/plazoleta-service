package com.plazoleta.plazoleta_service.infrastructure.output.jpa.adapter;

import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import com.plazoleta.plazoleta_service.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.RestaurantEntity;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return null;
    }

    @Override
    public Restaurant getRestaurantByIdOwner(Long idOwner) {
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findByIdOwner(idOwner);
        RestaurantEntity restaurantEntity = optionalRestaurantEntity.orElse(null);
        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return List.of();
    }

    @Override
    public List<Restaurant> getRestaurantsWithPagination(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public void deleteRestaurantById(Long id) {

    }

    @Override
    public Boolean existsByNit(String nit) {
        return restaurantRepository.existsByNit(nit);
    }
}
