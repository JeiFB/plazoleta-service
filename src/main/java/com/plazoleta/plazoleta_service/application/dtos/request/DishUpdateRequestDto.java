package com.plazoleta.plazoleta_service.application.dtos.request;


import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishUpdateRequestDto {

    @Pattern(regexp = "^[1-9]\\d*$", message = "El precio debe ser un número entero positivo mayor a cero")
    private String price;
    private String description;
}
