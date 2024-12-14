package com.plazoleta.plazoleta_service.application.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishRequestDto {
    @NotNull(message = "El Id del plato no puede ser nulo")
    private Long idDish;
    @NotNull(message = "La cantidad no puede ser nula")
    private Long amount;
}
