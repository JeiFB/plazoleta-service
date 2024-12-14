package com.plazoleta.plazoleta_service.infrastructure.input.rest;


import com.plazoleta.plazoleta_service.application.dtos.request.DishRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.request.DishUpdateRequestDto;
import com.plazoleta.plazoleta_service.application.dtos.response.DishResponseDto;
import com.plazoleta.plazoleta_service.application.handler.IDishHandler;
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
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Get all dishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All dishes returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DishResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<List<DishResponseDto>> getAllDishes(){
        return  ResponseEntity.ok(dishHandler.getAllDishes());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<DishResponseDto> getDishById(@PathVariable(value = "id")Long dishId){
        return ResponseEntity.ok(dishHandler.getDishById(dishId));
    }


    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists", content = @Content)
    })
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    @PostMapping("/")
    public ResponseEntity<Void> createDishInDishes(@Valid @RequestBody DishRequestDto dishRequestDto){
        dishHandler.createDishInDishes(dishRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<DishRequestDto> updateDish(@Valid @PathVariable(value = "id")Long dishId, @RequestBody DishUpdateRequestDto dishUpdateRequestDto){
        dishHandler.updateDish(dishId, dishUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/activate/{enableDisable}")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<DishRequestDto> updateEnableDisableDish(@PathVariable(value = "id")Long dishId, @PathVariable(value = "enableDisable")Long enableDisable){
        dishHandler.updateEnableDisableDish(dishId, enableDisable);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restaurant/{idRestaurant}/page/{page}/size/{size}")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<List<DishResponseDto>> getAllDishesByRestaurant(@PathVariable(value = "idRestaurant" ) Long idRestaurant,@PathVariable(value = "page" )Integer page, @PathVariable(value = "size") Integer size){

        return ResponseEntity.ok(dishHandler.dishesAllByRestaurantById(idRestaurant,page,size));
    }

}
