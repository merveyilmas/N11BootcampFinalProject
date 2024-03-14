package com.merveyilmaz.userservice.converter;

import com.merveyilmaz.userservice.dto.ConvertedRestaurantDTO;
import com.merveyilmaz.userservice.dto.RestaurantDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RestaurantConverter {

    public static List<ConvertedRestaurantDTO> convertToRestaurants(List<RestaurantDTO> dtoList) {
        List<ConvertedRestaurantDTO> restaurantList = new ArrayList<>();

        for (RestaurantDTO dto : dtoList) {

            double longitude = dto.longitude() / 1e6;
            double latitude = dto.latitude() / 1e6;

            restaurantList.add(new ConvertedRestaurantDTO(dto.id(), dto.name(), longitude, latitude));
        }

        return restaurantList;
    }
}
