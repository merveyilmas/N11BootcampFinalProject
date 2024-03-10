package com.merveyilmaz.restaurantservice.request;


public record RestaurantSaveRequest(String id,
                                    String name,
                                    String restaurantCreateDate,
                                    double longitude,
                                    double latitude) {
}
