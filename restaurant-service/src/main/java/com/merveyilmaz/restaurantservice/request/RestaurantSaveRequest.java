package com.merveyilmaz.restaurantservice.request;

import com.merveyilmaz.restaurantservice.enums.EnumRate;

public record RestaurantSaveRequest(Long userId,
                                    Long restaurantId,
                                    EnumRate rate,
                                    String comment) {
}
