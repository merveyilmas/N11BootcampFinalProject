package com.merveyilmaz.userservice.dto;

public record RestaurantDTO(int id,
                            String name,
                            String restaurantCreateDate,
                            int latitude,
                            int longitude) {
}
