package com.merveyilmaz.restaurantservice.dto;

import java.time.LocalDateTime;

public record RestaurantDTO(String id,
                            String name,
                            String restaurantCreateDate,
                            double longitude,
                            double latitude) {
}
