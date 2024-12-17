package com.plazoleta.plazoleta_service.application.dtos.response;

import com.plazoleta.plazoleta_service.domain.model.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String lastNane;
    private String documentNumber;
    private String cellNumber;
    private String email;
    private String password;
    private Rol rol;
}
