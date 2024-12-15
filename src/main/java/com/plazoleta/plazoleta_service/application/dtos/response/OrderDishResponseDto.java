package com.plazoleta.plazoleta_service.application.dtos.response;

import com.plazoleta.plazoleta_service.domain.model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishResponseDto {

    private Long id;
    private String name;
    private String price;
    private String description;
    private String imageUrl;
    private Category categoryId;
    private String amount;
}
