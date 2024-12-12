package com.plazoleta.plazoleta_service.infrastructure.input.rest;

import com.plazoleta.plazoleta_service.application.dtos.request.RestaurantAndEmployeeRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.RestaurantAndEmployeeResponseDto;
import com.plazoleta.plazoleta_service.application.handler.IRestaurantAndEmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantEmployee")
@RequiredArgsConstructor
public class RestaurantAndEmployeeRestController {
    private  final IRestaurantAndEmployeeHandler restaurantAndEmployeeHandler;

    @Operation(summary = "Add a new restaurant_employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant_employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant_employee already exists", content = @Content)
    })
    @PostMapping("/")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> saveRestaurantEmployee(@Valid @RequestBody RestaurantAndEmployeeRequestDto restaurantEmployeeRequestDto) {
        restaurantAndEmployeeHandler.saveRestaurantEmployee(restaurantEmployeeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Get all restaurant_employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All restaurant_employees returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestaurantAndEmployeeResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<List<RestaurantAndEmployeeResponseDto>> getAllRestaurantEmployees() {
        return ResponseEntity.ok(restaurantAndEmployeeHandler.getAllRestaurantEmployees());
    }
}
