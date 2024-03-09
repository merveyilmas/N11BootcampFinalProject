package com.merveyilmaz.restaurantservice.client;

import com.merveyilmaz.restaurantservice.dto.UserDTO;
import com.merveyilmaz.restaurantservice.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user", url = "http://localhost:8080/api/v1/users")
public interface UserClient {

    @GetMapping("/with-coordinates")
    RestResponse<UserDTO> getRestaurantByCoordinates(@PathVariable double latitude, @PathVariable double longitude);
}
