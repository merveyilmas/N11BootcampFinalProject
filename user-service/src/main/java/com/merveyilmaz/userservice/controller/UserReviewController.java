package com.merveyilmaz.userservice.controller;

import com.merveyilmaz.userservice.controller.concract.UserReviewControllerContract;
import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.dto.UserReviewDTO;
import com.merveyilmaz.userservice.general.RestResponse;
import com.merveyilmaz.userservice.request.UserReviewSaveRequest;
import com.merveyilmaz.userservice.request.UserReviewUpdateCommentAndScoreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userReviews")
public class UserReviewController {

    private UserReviewControllerContract userReviewControllerContract;

    public UserReviewController(UserReviewControllerContract userReviewControllerContract) {
        this.userReviewControllerContract = userReviewControllerContract;
    }

    @GetMapping()
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> getAllUserReviews() {
        List<UserReviewDTO> userReviews = userReviewControllerContract.getAllUserReviews();
        return ResponseEntity.ok(RestResponse.of(userReviews));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDTO>> saveUserReview(@RequestBody UserReviewSaveRequest request) {

        UserReviewDTO userReviewDTO = userReviewControllerContract.saveUserReview(request);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUserReview(@PathVariable Long id) {
        userReviewControllerContract.deleteUserReview(id);
    }

    @PatchMapping("/{id}/comment-and-score")
    public ResponseEntity<RestResponse<UserReviewDTO>> updateCommentAndScore(@PathVariable Long id, @RequestParam UserReviewUpdateCommentAndScoreRequest newCommentAndScore) {

        UserReviewDTO userReviewDTO = userReviewControllerContract.updateCommentAndScore(id, newCommentAndScore);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }
}
