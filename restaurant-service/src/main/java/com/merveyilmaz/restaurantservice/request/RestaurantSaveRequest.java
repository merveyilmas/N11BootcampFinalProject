package com.merveyilmaz.restaurantservice.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RestaurantSaveRequest(String id,
                                    @NotBlank String name,
                                    @NotNull int longitude,
                                    @NotNull int latitude) {
}
