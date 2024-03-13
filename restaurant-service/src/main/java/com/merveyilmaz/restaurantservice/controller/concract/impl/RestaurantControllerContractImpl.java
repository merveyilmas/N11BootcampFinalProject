package com.merveyilmaz.restaurantservice.controller.concract.impl;

import com.merveyilmaz.restaurantservice.controller.concract.RestaurantControllerContract;
import com.merveyilmaz.restaurantservice.dto.RestaurantDTO;
import com.merveyilmaz.restaurantservice.entitiy.Restaurant;
import com.merveyilmaz.restaurantservice.mapper.RestaurantMapper;
import com.merveyilmaz.restaurantservice.request.RestaurantSaveRequest;
import com.merveyilmaz.restaurantservice.request.RestaurantUpdateRequest;
import com.merveyilmaz.restaurantservice.service.serviceEntity.RestaurantEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantEntityService restaurantEntityService;

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantEntityService.findAll();
        return RestaurantMapper.INSTANCE.convertToRestaurantDTOs(restaurants);
    }

    @Override
    public RestaurantDTO saveRestaurant(RestaurantSaveRequest request) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.convertToRestaurant(request);

        restaurant = restaurantEntityService.save(restaurant);

        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }

    @Override
    public void deleteRestaurant(String id) {
        restaurantEntityService.delete(id);
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantUpdateRequest request) {
        Restaurant restaurant = restaurantEntityService.findByIdWithControl(request.id());
        RestaurantMapper.INSTANCE.updateUserFields(restaurant, request);

        restaurantEntityService.save(restaurant);

        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }

}
