package com.merveyilmaz.restaurantservice.controller.concract.impl;

import com.merveyilmaz.restaurantservice.controller.concract.RestaurantControllerContract;
import com.merveyilmaz.restaurantservice.dto.RestaurantDTO;
import com.merveyilmaz.restaurantservice.request.RestaurantSaveRequest;
import com.merveyilmaz.restaurantservice.request.RestaurantUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return null;
    }

    @Override
    public RestaurantDTO saveRestaurant(RestaurantSaveRequest request) {
        return null;
    }

    @Override
    public void deleteRestaurant(Long id) {

    }
    @Override
    public RestaurantDTO updateRestaurant(RestaurantUpdateRequest request) {
        return null;
    }

}
