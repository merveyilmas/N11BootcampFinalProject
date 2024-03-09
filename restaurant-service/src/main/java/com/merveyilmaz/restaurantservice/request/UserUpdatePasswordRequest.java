package com.merveyilmaz.restaurantservice.request;

public record UserUpdatePasswordRequest(String oldPass,
                                        String newPass) {
}
