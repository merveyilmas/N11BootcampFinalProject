package com.merveyilmaz.userservice.dto;

public record RestaurantWithRateDTO(int id,
                                    String name,
                                    int latitude,
                                    int longitude,
                                    double rate) {
}
