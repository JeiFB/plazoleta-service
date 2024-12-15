package com.plazoleta.plazoleta_service.infrastructure.output.jpa.adapter;

import com.plazoleta.plazoleta_service.domain.model.RestaurantAndEmployee;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IRestaurantAndEmployeePersistencePort;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.entity.RestaurantAndEmployeeEntity;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IRestaurantAndEmployeeEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IRestaurantAndEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantAndEmployeeJpaAdapter implements IRestaurantAndEmployeePersistencePort {

    private final IRestaurantAndEmployeeRepository restaurantAndEmployeeRepository;
    private  final IRestaurantAndEmployeeEntityMapper restaurantAndEmployeeEntityMapper;


    @Override
    public RestaurantAndEmployee saveRestaurantEmployee(RestaurantAndEmployee restaurantEmployee) {
        RestaurantAndEmployeeEntity restaurantEmployeeEntity = restaurantAndEmployeeRepository.save(restaurantAndEmployeeEntityMapper.toEntity(restaurantEmployee));
        return restaurantAndEmployeeEntityMapper.toRestaurantEmployeeModel(restaurantEmployeeEntity);
    }

    @Override
    public List<RestaurantAndEmployee> getAllRestaurantEmployees() {
        List<RestaurantAndEmployeeEntity> restaurantEmployeeEntityList = restaurantAndEmployeeRepository.findAll();
        if(restaurantEmployeeEntityList.isEmpty()){
            throw new IllegalArgumentException("no hay datos");
        }
        return restaurantAndEmployeeEntityMapper.toRestaurantEmployeeModelList(restaurantEmployeeEntityList);
    }

    @Override
    public RestaurantAndEmployee findByEmployeeId(Long idEmployee) {
        Optional<RestaurantAndEmployeeEntity> restaurantEmployeeEntityOptional = restaurantAndEmployeeRepository.findByEmployeeId(idEmployee);
        RestaurantAndEmployeeEntity restaurantEmployeeEntity = restaurantEmployeeEntityOptional.orElse(null);
        return restaurantAndEmployeeEntityMapper.toRestaurantEmployeeModel(restaurantEmployeeEntity);
    }


}
