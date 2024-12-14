package com.plazoleta.plazoleta_service.application.dtos.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishResponseDto {
    @NotNull(message = "El ID del plato no puede ser nulo")
    private Long idDish;
    @NotNull(message = "La cantidad no puede ser nula")
    private Long amount;
}
