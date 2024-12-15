package com.plazoleta.plazoleta_service.domain.model.orders;

import com.plazoleta.plazoleta_service.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishResponseModel {
    private Long id;
    private String name;
    private String price;
    private String description;
    private String urlImg;
    private Category categoryId;
    private String amount;
}
