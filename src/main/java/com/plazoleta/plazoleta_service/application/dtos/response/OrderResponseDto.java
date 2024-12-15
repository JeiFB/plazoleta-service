package com.plazoleta.plazoleta_service.application.dtos.response;

import com.plazoleta.plazoleta_service.application.dtos.request.OrderDishRequestDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long idClient;
    private Long idChef;
    private Date date;
    private List<OrderDishResponseDto> orderDishes;
}
