package com.plazoleta.plazoleta_service.domain.usecase;

import com.plazoleta.plazoleta_service.domain.model.Dish;
import com.plazoleta.plazoleta_service.domain.model.Restaurant;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IDishPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.persistence.IRestaurantPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.bearertoken.IToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IToken token;

    @InjectMocks
    private DishUseCase dishUseCase;

    private Dish mockDish;
    private Restaurant mockRestaurant;

    @BeforeEach
    void setUp() {
        mockRestaurant = new Restaurant();
        mockRestaurant.setId(1L);
        mockRestaurant.setIdOwner(100L);

        mockDish = new Dish();
        mockDish.setId(1L);
        mockDish.setName("Mock Dish");
        mockDish.setPrice("15.99");
        mockDish.setDescription("Mock Description");
        mockDish.setRestaurantId(mockRestaurant);
        mockDish.setActive(true);
    }

    @Test
    void getDishById_ShouldReturnDish() {
        when(dishPersistencePort.getDishById(1L)).thenReturn(mockDish);

        Dish result = dishUseCase.getDishById(1L);

        assertNotNull(result);
        assertEquals("Mock Dish", result.getName());
        verify(dishPersistencePort, times(1)).getDishById(1L);
    }

    @Test
    void getAllDishes_ShouldReturnListOfDishes() {
        when(dishPersistencePort.getAllDishes()).thenReturn(Arrays.asList(mockDish));

        List<Dish> result = dishUseCase.getAllDishes();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(dishPersistencePort, times(1)).getAllDishes();
    }

    @Test
    void createDish_ShouldCreateDishSuccessfully() {
        when(restaurantPersistencePort.getRestaurantById(1L)).thenReturn(mockRestaurant);
        when(token.getBearerToken()).thenReturn("BearerToken");
        when(token.getUserAuthenticationId("BearerToken")).thenReturn(100L);

        dishUseCase.createDish(mockDish);

        verify(dishPersistencePort, times(1)).createDish(mockDish);
    }

    @Test
    void createDish_ShouldThrowExceptionWhenRestaurantDoesNotExist() {
        when(restaurantPersistencePort.getRestaurantById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.createDish(mockDish));

        assertEquals("No existe restaurante", exception.getMessage());
        verify(dishPersistencePort, never()).createDish(any());
    }

    @Test
    void updateDish_ShouldUpdateDishDetails() {
        Dish updatedDish = new Dish();
        updatedDish.setPrice("19.99");
        updatedDish.setDescription("Updated Description");

        when(dishPersistencePort.getDishById(1L)).thenReturn(mockDish);

        dishUseCase.updateDish(1L, updatedDish);

        verify(dishPersistencePort, times(1)).createDish(mockDish);
        assertEquals("19.99", mockDish.getPrice());
        assertEquals("Updated Description", mockDish.getDescription());
    }

    @Test
    void updateDish_ShouldThrowExceptionWhenDishDoesNotExist() {
        when(dishPersistencePort.getDishById(1L)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> dishUseCase.updateDish(1L, mockDish));

        assertEquals("Este plato no existe", exception.getMessage());
        verify(dishPersistencePort, never()).createDish(any());
    }



    @Test
    void dishesAllByRestaurantById_ShouldReturnActiveDishes() {
        Dish inactiveDish = new Dish();
        inactiveDish.setActive(false);

        when(dishPersistencePort.dishesAllByRestaurantId(1L, 0, 10))
                .thenReturn(Arrays.asList(mockDish, inactiveDish));

        List<Dish> result = dishUseCase.dishesAllByRestaurantById(1L, 0, 10);

        assertEquals(1, result.size());
        assertTrue(result.get(0).getActive());
    }

    @Test
    void validateOwnerAuthWithOwnerRestaurant_ShouldThrowExceptionWhenNotOwner() {
        when(token.getBearerToken()).thenReturn("BearerToken");
        when(token.getUserAuthenticationId("BearerToken")).thenReturn(101L);
        when(restaurantPersistencePort.getRestaurantById(1L)).thenReturn(mockRestaurant);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> dishUseCase.createDish(mockDish));

        assertEquals("No es propietario de este restaurante", exception.getMessage());
    }
}
