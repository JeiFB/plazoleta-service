package com.plazoleta.plazoleta_service.application.dtos.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
public class RestaurantRequestDto {
    @NotBlank(message = "Nombre invalido")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[0-9a-zA-Z ]+$", message = "El nombre puede contener números con letras pero no solo números")
    private String name;

    @NotBlank(message = "Nit Invalido")
    @Column(unique = true)
    @Pattern(regexp = "\\d+", message = "El nit debe ser númerico")
    private String nit;

    @NotBlank(message = "Direccion invalida")
    private String address;

    @NotBlank(message = "Numero de telefono invalido")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "El telefono debe contener máximo 13 caracteres y debe contener el simbolo '+' al inicio")
    private String cellNumber;

    @NotBlank(message = "Imangen necesaria")
    private String urlLogo;

    @NotNull(message = "El id_propietario no puede ser nulo")
    @Min(value = 1, message = "El id_propietario debe ser mayor a cero")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long idOwner;
}
