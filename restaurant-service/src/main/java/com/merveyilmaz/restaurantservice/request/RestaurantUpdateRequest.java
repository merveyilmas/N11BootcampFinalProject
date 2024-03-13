package com.merveyilmaz.restaurantservice.request;

public record RestaurantUpdateRequest(String id,
                                      String name,
                                      int longitude,
                                      int latitude) {
}
