package com.merveyilmaz.userservice.request;

import com.merveyilmaz.userservice.enums.EnumRate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserReviewSaveRequest(@NotNull Long userId,
                                    @NotNull Long restaurantId,
                                    @NotBlank EnumRate rate,
                                    String comment) {
}
