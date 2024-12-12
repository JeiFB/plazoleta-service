package com.plazoleta.plazoleta_service.application.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;


@Getter
@Setter
public class DishRequestDto {
    @NotBlank(message = "Nombre del plato invalido")
    private String name;
    @NotBlank(message = "Precio invalido")
    @Pattern(regexp = "^[1-9]\\d*$", message = "El precio debe ser un número entero positivo mayor a cero")
    private String price;
    @NotBlank(message = "Descripcion invalida")
    private String description;
    @NotBlank(message = "Url invalida")
    private String imageUrl;

    @NotNull(message = "El restaurante id no puede ser nulo")
    @Min(value = 1, message = "El restaurante id debe ser mayor a cero")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long restaurantId;

    @NotNull(message = "La categoria no puede ser nulo")
    @Min(value = 1, message = "La categoria debe ser mayor a cero")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long category;

}
