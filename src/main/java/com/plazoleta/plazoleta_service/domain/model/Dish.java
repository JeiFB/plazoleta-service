package com.plazoleta.plazoleta_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private Long id;
    private String name;
    private String price;
    private String description;
    private String imageUrl;
    private Category category;
    private Restaurant restaurantId;
    private Boolean active;
}
