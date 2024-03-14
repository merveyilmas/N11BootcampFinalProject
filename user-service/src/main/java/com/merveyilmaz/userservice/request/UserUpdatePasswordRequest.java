package com.merveyilmaz.userservice.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdatePasswordRequest(@NotBlank String oldPass,
                                        @NotBlank String newPass) {
}
