package com.merveyilmaz.userservice.request;

import com.merveyilmaz.userservice.enums.EnumRate;

public record UserReviewUpdateCommentAndScoreRequest(String newComment,
                                                     EnumRate newScore) {
}
