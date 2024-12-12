package com.plazoleta.plazoleta_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantAndEmployee {
    private Long id;
    private Long restaurantId;
    private Long personId;
}
