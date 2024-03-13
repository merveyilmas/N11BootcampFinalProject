package com.merveyilmaz.restaurantservice.request;

public record RestaurantUpdateRequest(String id,
                                      String name,
                                      String restaurantCreateDate,
                                      int longitude,
                                      int latitude) {
}
