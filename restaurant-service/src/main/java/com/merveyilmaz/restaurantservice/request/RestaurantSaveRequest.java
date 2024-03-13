package com.merveyilmaz.restaurantservice.request;


public record RestaurantSaveRequest(String id,
                                    String name,
                                    String restaurantCreateDate,
                                    int longitude,
                                    int latitude) {
}
