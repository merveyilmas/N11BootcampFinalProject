package com.merveyilmaz.restaurantservice.dto;

import java.time.LocalDateTime;

public record RestaurantDTO(Long id,
                            String name,
                            LocalDateTime restaurantCreateDate,
                            double longitude,
                            double latitude) {
}
