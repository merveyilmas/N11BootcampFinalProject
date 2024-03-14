package com.merveyilmaz.userservice.dto;

import java.time.LocalDateTime;

public record ConvertedRestaurantDTO(int id,
                                     String name,
                                     double latitude,
                                     double longitude) {
}
