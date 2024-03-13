package com.merveyilmaz.restaurantservice.controller.concract.impl;

import com.merveyilmaz.restaurantservice.controller.concract.RestaurantControllerContract;
import com.merveyilmaz.restaurantservice.dto.RestaurantDTO;
import com.merveyilmaz.restaurantservice.entitiy.Restaurant;
import com.merveyilmaz.restaurantservice.mapper.RestaurantConverter;
import com.merveyilmaz.restaurantservice.request.RestaurantSaveRequest;
import com.merveyilmaz.restaurantservice.request.RestaurantUpdateRequest;
import com.merveyilmaz.restaurantservice.service.serviceEntity.RestaurantEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantEntityService restaurantEntityService;

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        Iterable<Restaurant> restaurants = restaurantEntityService.findAll();
        return RestaurantConverter.convertToRestaurantDTOs(restaurants);
    }

    @Override
    public RestaurantDTO saveRestaurant(RestaurantSaveRequest request) {
        Restaurant restaurant = RestaurantConverter.convertToRestaurant(request);

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        restaurant.setRestaurantCreateDate(formattedDateTime);
        restaurant = restaurantEntityService.save(restaurant);

        return RestaurantConverter.convertToRestaurantDTO(restaurant);
    }

    @Override
    public void deleteRestaurant(String id) {
        restaurantEntityService.delete(id);
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantUpdateRequest request) {
        Restaurant restaurant = restaurantEntityService.findByIdWithControl(request.id());
        RestaurantConverter.updateUserFields(restaurant, request);

        restaurantEntityService.save(restaurant);

        return RestaurantConverter.convertToRestaurantDTO(restaurant);
    }

}
