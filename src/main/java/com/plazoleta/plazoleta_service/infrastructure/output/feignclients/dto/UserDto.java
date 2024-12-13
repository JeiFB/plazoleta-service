package com.plazoleta.plazoleta_service.infrastructure.output.feignclients.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String lastNane;
    private String numberCell;
    private String document;
    private String email;
    private String password;
    private RolDto rol;
}
