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
    private String lastName;
    private String document;
    private String numberCell;
    private String email;
    private String password;
    private Rol rol;
}
