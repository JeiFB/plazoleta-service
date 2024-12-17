package com.plazoleta.plazoleta_service.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsMessageRequestDto {
    @NotBlank(message = "El numero celular es requerido")
    @Pattern(regexp = "^\\+57\\d{10}$", message = "El numero debe empezar con +57 seguido de 10 digitos (numero colombiano)")
    private String number;

    @NotBlank(message = "El mensaje es requerido")
    private String message;
}
