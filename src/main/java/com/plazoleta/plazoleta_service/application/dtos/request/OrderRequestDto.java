package com.plazoleta.plazoleta_service.application.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    @NotNull(message = "Lista de platos no puede ser nulo")
    @Size(min = 1, message = "Debe de haber al menos un plato en la lista")
    private List<OrderDishRequestDto> dishes;
    @NotNull(message = "Id restaurante no puede ser nulo")
    private Long restaurantId;
}
