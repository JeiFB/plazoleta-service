package com.plazoleta.plazoleta_service.domain.usecase;

import com.plazoleta.plazoleta_service.domain.api.IDishServicePort;
import com.plazoleta.plazoleta_service.domain.model.Dish;
import com.plazoleta.plazoleta_service.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta_service.domain.spi.bearertoken.IToken;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IToken token;


    @Override
    public Dish getDishById(Long id) {
        return dishPersistencePort.getDishById(id);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public void createDish(Dish dish) {
        dish.setActive(true);
        dishPersistencePort.createDish(dish);
    }

    @Override
    public void updateDish(Long id, Dish dish) {
        // Buscar el plato
        Dish dishInternal = dishPersistencePort.getDishById(id);
        // Validar si el plato existe
        if (dishInternal == null) {
            throw new IllegalArgumentException("Este plato no existe");
        }
        // Actualizar solo si el precio no es nulo ni vacío
        if (dish.getPrice() != null && !dish.getPrice().trim().isEmpty()) {
            dishInternal.setPrice(dish.getPrice());
        }
        // Actualizar solo si la descripción no es nula ni vacía
        if (dish.getDescription() != null && !dish.getDescription().trim().isEmpty()) {
            dishInternal.setDescription(dish.getDescription());
        }
        // Persistir los cambios
        dishPersistencePort.createDish(dishInternal);
    }

    @Override
    public List<Dish> dishesAllByRestaurantById(Long idRestaurant, Integer page, Integer size) {
        List<Dish> dishModelList=dishPersistencePort.dishesAllByRestaurantId(idRestaurant, page,size);
        List<Dish> platosActivos=new ArrayList<>();
        for (Dish dishModel:dishModelList) {
            if(dishModel.getActive()){
                platosActivos.add(dishModel);
            }
        }
        return platosActivos;
    }

    private void validateOwnerAuthWithOwnerRestaurant(Dish dish){
        String bearerToken = token.getBearerToken();
        if(bearerToken==null) throw new IllegalArgumentException();
        Long idOwnerAuth = token.getUserAuthenticationId(bearerToken);
        Long idOwnerRestaurant =  restaurantPersistencePort.getRestaurantById(dish.getRestaurantId().getId()).getIdOwner();
        if(idOwnerAuth!=idOwnerRestaurant) throw new IllegalArgumentException();
    }
}
