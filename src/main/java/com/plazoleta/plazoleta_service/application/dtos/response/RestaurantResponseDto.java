package com.plazoleta.plazoleta_service.application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RestaurantResponseDto {

    private Long id;
    private String name;
    private String nit;
    private String address;
    private String cellNumber;
    private String urlLogo;
    private Long idOwner;
}
