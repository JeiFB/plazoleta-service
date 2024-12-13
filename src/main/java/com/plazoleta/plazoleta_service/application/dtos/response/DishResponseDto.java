package com.plazoleta.plazoleta_service.application.dtos.response;


import com.plazoleta.plazoleta_service.domain.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponseDto {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Category category;
    private Boolean active;

}