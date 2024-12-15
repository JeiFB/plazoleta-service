package com.plazoleta.plazoleta_service.domain.model.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseModel {
    private Long id;
    private Long idClient;
    private Long idChef;
    private Date date;
    private List<OrderDishResponseModel> orderDishes;
}
