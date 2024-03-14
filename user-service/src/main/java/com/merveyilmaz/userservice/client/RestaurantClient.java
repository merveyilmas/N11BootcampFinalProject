package com.merveyilmaz.userservice.client;

import com.merveyilmaz.userservice.dto.RestaurantDTO;
import com.merveyilmaz.userservice.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "restaurant", url = "http://localhost:8081/api/v1/restaurants")
public interface RestaurantClient {

    @GetMapping
    RestResponse<List<RestaurantDTO>> getAllRestaurants();
}
