package com.plazoleta.plazoleta_service.infrastructure.input.rest;

import com.plazoleta.plazoleta_service.application.dtos.request.OrderRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.OrderResponseDto;
import com.plazoleta.plazoleta_service.application.handler.IOrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final IOrderHandler orderHandler;

    @Operation(summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/placeAnOrder")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<OrderResponseDto> placeAnOrder(@Validated @RequestBody OrderRequestDto orderRequest) {
        orderHandler.saveOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get Orders By State")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Orders found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Orders don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @GetMapping("/getOrdersByStatePaginated/page/{page}/size/{size}/status/{state}")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<List<OrderResponseDto>> getAllOrderByState(@PathVariable Integer page, @PathVariable Integer size, @PathVariable(value = "state") String state) {
        if (size <= 0L || page < 0L || state.isBlank() || state.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(orderHandler.getAllOrdersWithPagination(page, size, state));
    }

    @Operation(summary = "Take order and update status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order taken", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/takeOrderAndUpdateStatus/{idOrder}/status/{status}")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<Void> takeOrderAndUpdateStatus(@PathVariable Long idOrder, @PathVariable String status) {
        if (idOrder <= 0L || status.isBlank() || status.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        orderHandler.takeOrderAndUpdateStatus(idOrder, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Order ready")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order ready", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/updateAndNotifyOrderReady/{idOrder}")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<Void> updateAndNotifyOrderReady(@PathVariable Long idOrder) {
        if (idOrder <= 0L) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        orderHandler.updateAndNotifyOrderReady(idOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
