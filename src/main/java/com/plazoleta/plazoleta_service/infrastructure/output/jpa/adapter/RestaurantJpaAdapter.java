package com.plazoleta.plazoleta_service.infrastructure.output.jpa.adapter;

import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IRestaurantPersistencePort;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.RestaurantEntity;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findById(id);
        RestaurantEntity restaurantEntity = optionalRestaurantEntity.orElse(null);
        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public Restaurant getRestaurantByIdOwner(Long idOwner) {
        Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findByIdOwner(idOwner);
        RestaurantEntity restaurantEntity = optionalRestaurantEntity.orElse(null);
        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        List<RestaurantEntity> restaurantEntityList =  restaurantRepository.findAll();
        if(restaurantEntityList.isEmpty()) throw new IllegalArgumentException("no hay datos");
        return restaurantEntityMapper.toRestaurantModelList(restaurantEntityList);
    }

    @Override
    public List<Restaurant> getRestaurantsWithPagination(Integer page, Integer size) {
        PageRequest pages = PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,"name"));
        Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pages);
        List<RestaurantEntity> restaurantEntityList = restaurantPage.getContent();
        if(restaurantEntityList.isEmpty()){
            throw new IllegalArgumentException("No hay datos");
        }
        return restaurantEntityMapper.toRestaurantModelList(restaurantEntityList);
    }

    @Override
    public void deleteRestaurantById(Long id) {

    }

    @Override
    public Boolean existsByNit(String nit) {
        return restaurantRepository.existsByNit(nit);
    }
}
