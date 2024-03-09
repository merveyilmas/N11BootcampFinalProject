package com.merveyilmaz.userservice.request;

public record UserUpdatePasswordRequest(String oldPass,
                                        String newPass) {
}
