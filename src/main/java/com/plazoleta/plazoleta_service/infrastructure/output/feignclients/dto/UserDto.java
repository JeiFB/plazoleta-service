package com.plazoleta.plazoleta_service.infrastructure.output.feignclients.dto;

import com.plazoleta.plazoleta_service.domain.model.Rol;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String lastNane;
    private String documentNumber;
    private String cellNumber;
    private String email;
    private String password;
    private Rol rol;
}
