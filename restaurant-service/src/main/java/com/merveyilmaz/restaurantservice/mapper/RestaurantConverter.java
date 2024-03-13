package com.merveyilmaz.restaurantservice.mapper;

import com.merveyilmaz.restaurantservice.dto.RestaurantDTO;
import com.merveyilmaz.restaurantservice.entitiy.Restaurant;
import com.merveyilmaz.restaurantservice.request.RestaurantSaveRequest;
import com.merveyilmaz.restaurantservice.request.RestaurantUpdateRequest;

import java.util.ArrayList;
import java.util.List;

public class RestaurantConverter {

    public static Restaurant convertToRestaurant(RestaurantSaveRequest request) {

        System.out.println("request.latitude()");
        System.out.println(request.latitude());

        Restaurant restaurant = new Restaurant();
        restaurant.setId(request.id());
        restaurant.setName(request.name());
        restaurant.setLatitude(request.latitude());
        restaurant.setLongitude(request.longitude());

        return restaurant;
    }

    public static RestaurantDTO convertToRestaurantDTO(Restaurant restaurant) {
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getRestaurantCreateDate(),
                restaurant.getLongitude(),
                restaurant.getLatitude()
        );
    }

    public static List<RestaurantDTO> convertToRestaurantDTOs(Iterable<Restaurant> restaurants) {
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDTOs.add(convertToRestaurantDTO(restaurant));
        }
        return restaurantDTOs;
    }

    public static void updateUserFields(Restaurant restaurant, RestaurantUpdateRequest request) {
        restaurant.setId(request.id());
        restaurant.setName(request.name());
        restaurant.setLongitude(request.longitude());
        restaurant.setLatitude(request.latitude());
    }
}