package com.plazoleta.plazoleta_service.application.dtos.response;

import com.plazoleta.plazoleta_service.application.dtos.request.OrderDishRequestDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    @NotNull(message = "La lista de platos no puede ser nula")
    @Size(min = 1, message = "Debe haber al menos un plato en la lista")
    private List<OrderDishRequestDto> dishes;
    @NotNull(message = "El identificador del restaurante no puede ser nulo")
    private Long restaurantId;
}
