package com.merveyilmaz.userservice.controller.concract;

import com.merveyilmaz.userservice.dto.UserReviewDTO;
import com.merveyilmaz.userservice.request.UserReviewSaveRequest;
import com.merveyilmaz.userservice.request.UserReviewUpdateCommentAndScoreRequest;
import org.springframework.web.bind.annotation.*;

public interface UserReviewControllerContract {

    UserReviewDTO saveUserReview(@RequestBody UserReviewSaveRequest request);

    void deleteUserReview(@PathVariable Long id);

    UserReviewDTO updateCommentAndScore(@PathVariable Long id, @RequestParam UserReviewUpdateCommentAndScoreRequest newCommentAndScore);
}
