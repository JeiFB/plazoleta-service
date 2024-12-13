package com.plazoleta.plazoleta_service.infrastructure.configuration;

import com.plazoleta.plazoleta_service.domain.api.IDishServicePort;
import com.plazoleta.plazoleta_service.domain.api.IRestaurantAndEmployeeServicePort;
import com.plazoleta.plazoleta_service.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta_service.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.IRestaurantAndEmployeePersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.bearertoken.IToken;
import com.plazoleta.plazoleta_service.domain.spi.feignClients.IUserFeignClientPort;
import com.plazoleta.plazoleta_service.domain.usecase.DishUseCase;
import com.plazoleta.plazoleta_service.domain.usecase.RestaurantAndEmployeeUseCase;
import com.plazoleta.plazoleta_service.domain.usecase.RestaurantUseCase;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.IUserFeignClients;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.adapter.UserFeignAdapter;
import com.plazoleta.plazoleta_service.infrastructure.output.feignclients.mapper.IUserDtoMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.adapter.DishJpaAdapter;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.adapter.RestaurantAndEmployeeJpaAdapter;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IRestaurantAndEmployeeEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IDishRepository;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IRestaurantAndEmployeeRepository;
import com.plazoleta.plazoleta_service.infrastructure.output.jpa.repository.IRestaurantRepository;
import com.plazoleta.plazoleta_service.infrastructure.output.token.TokeAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final  IRestaurantRepository restaurantRepository;
    private  final IRestaurantEntityMapper restaurantEntityMapper;

    private  final IDishRepository dishRepository;
    private final  IDishEntityMapper dishEntityMapper;

    private final IUserFeignClients IUserFeignClients;
    private final IUserDtoMapper userDtoMapper;

    private final IRestaurantAndEmployeeRepository restaurantAndEmployeeRepository;
    private final IRestaurantAndEmployeeEntityMapper restaurantAndEmployeeEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    //  Bean Dishes
    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort(), userFeignClientPort());
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort(), restaurantPersistencePort(),token());
    }

    @Bean
    public IUserFeignClientPort userFeignClientPort(){
        return  new UserFeignAdapter(IUserFeignClients, userDtoMapper);
    }

    @Bean
    public IRestaurantAndEmployeePersistencePort restaurantAndEmployeePersistencePort(){
        return  new RestaurantAndEmployeeJpaAdapter(restaurantAndEmployeeRepository, restaurantAndEmployeeEntityMapper);
    }

    @Bean
    public IRestaurantAndEmployeeServicePort restaurantAndEmployeeServicePort(){
        return  new RestaurantAndEmployeeUseCase(restaurantAndEmployeePersistencePort());
    }

    @Bean
    public IToken token(){
        return new TokeAdapter();
    }

}
