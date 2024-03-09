package com.merveyilmaz.userservice.dto;

import com.merveyilmaz.userservice.enums.EnumRate;

public record UserReviewDTO(Long id,
                            Long userId,
                            Long restaurantId,
                            EnumRate rate,
                            String comment) {
}
