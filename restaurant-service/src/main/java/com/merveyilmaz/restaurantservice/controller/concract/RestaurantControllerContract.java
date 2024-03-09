package com.merveyilmaz.restaurantservice.controller.concract;

import com.merveyilmaz.restaurantservice.dto.RestaurantDTO;
import com.merveyilmaz.restaurantservice.request.RestaurantSaveRequest;
import com.merveyilmaz.restaurantservice.request.RestaurantUpdateRequest;

import java.util.List;

public interface RestaurantControllerContract {

    List<RestaurantDTO> getAllRestaurants();

    RestaurantDTO saveRestaurant(RestaurantSaveRequest request);

    void deleteRestaurant(Long id);

    RestaurantDTO updateRestaurant(RestaurantUpdateRequest request);
}
