package com.merveyilmaz.userservice.request;

import com.merveyilmaz.userservice.enums.EnumRate;

public record UserReviewSaveRequest(Long userId,
                                    Long restaurantId,
                                    EnumRate rate,
                                    String comment) {
}
