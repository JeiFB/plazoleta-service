package com.plazoleta.plazoleta_service.application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private  Long id;
    private Long restaurantId;
    private Long personId;
}
