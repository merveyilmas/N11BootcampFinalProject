package com.merveyilmaz.userservice.response;

public record UserReviewDetailDTO(Long id,
                                  Long userId,
                                  String userName,
                                  String userSurname,
                                  String userFullName,
                                  Long productId,
                                  String productName,
                                  Long categoryId,
                                  String categoryName) {
}
