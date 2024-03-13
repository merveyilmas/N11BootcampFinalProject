package com.merveyilmaz.restaurantservice.request;


public record RestaurantSaveRequest(String id,
                                    String name,
                                    int longitude,
                                    int latitude) {
}
