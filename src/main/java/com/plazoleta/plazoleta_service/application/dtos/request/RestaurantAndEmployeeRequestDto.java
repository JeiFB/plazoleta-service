package com.plazoleta.plazoleta_service.application.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantAndEmployeeRequestDto {
    @NotNull(message = "El id del restaurante es requerido")
    @Min(value = 1, message = "El ID del empleado debe ser mayor a 0")
    private Long restaurantId;
    @NotNull(message = "El ide del empleado es requerido")
    @Min(value = 1, message = "El ID del empleado debe ser mayor a 0")
    private  Long employeeId;
}
