package com.plazoleta.plazoleta_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String lastNane;
    private String documentNumber;
    private String cellNumber;
    private String email;
    private String password;
    private Rol rol;
}
