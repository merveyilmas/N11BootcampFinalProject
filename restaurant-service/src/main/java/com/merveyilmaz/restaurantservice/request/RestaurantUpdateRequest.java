package com.merveyilmaz.restaurantservice.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RestaurantUpdateRequest(Long id,
                                      String name,
                                      LocalDateTime restaurantCreateDate,
                                      double longitude,
                                      double latitude) {
}
