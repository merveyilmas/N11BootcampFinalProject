package com.merveyilmaz.restaurantservice.controller;

import com.merveyilmaz.restaurantservice.controller.concract.RestaurantControllerContract;
import com.merveyilmaz.restaurantservice.dto.RestaurantDTO;
import com.merveyilmaz.restaurantservice.general.RestResponse;
import com.merveyilmaz.restaurantservice.request.RestaurantSaveRequest;
import com.merveyilmaz.restaurantservice.request.RestaurantUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@Validated
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantControllerContract restaurantControllerContract;

    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> getAllRestaurants() {
        List<RestaurantDTO> allRestaurants = restaurantControllerContract.getAllRestaurants();
        return ResponseEntity.ok(RestResponse.of(allRestaurants));
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody @Valid RestaurantSaveRequest request) {

        RestaurantDTO restaurantDTO = restaurantControllerContract.saveRestaurant(request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable @NotNull String id) {
        restaurantControllerContract.deleteRestaurant(id);
    }

    @PutMapping("/{debugRestaurantId}")
    public ResponseEntity<RestResponse<RestaurantDTO>> updateRestaurant(@PathVariable Long debugRestaurantId, @RequestBody @Valid RestaurantUpdateRequest request) {
        RestaurantDTO restaurantDTO = restaurantControllerContract.updateRestaurant(request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }
}
