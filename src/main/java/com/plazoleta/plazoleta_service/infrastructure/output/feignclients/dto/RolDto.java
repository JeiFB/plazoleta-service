package com.plazoleta.plazoleta_service.infrastructure.output.feignclients.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDto {
    private Long id;
    private String nameRol;
    private String descriptionRol;
}
