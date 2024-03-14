package com.merveyilmaz.userservice.controller;

import com.merveyilmaz.userservice.controller.concract.UserReviewControllerContract;
import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.dto.UserReviewDTO;
import com.merveyilmaz.userservice.general.RestResponse;
import com.merveyilmaz.userservice.request.UserReviewSaveRequest;
import com.merveyilmaz.userservice.request.UserReviewUpdateCommentAndScoreRequest;
import com.merveyilmaz.userservice.service.KafkaProducerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userReviews")
@Validated
@RequiredArgsConstructor
public class UserReviewController {

    private final UserReviewControllerContract userReviewControllerContract;
    private final KafkaProducerService kafkaProducerService;
    @Value("${kafka-info-log-topic}")
    private String INFO_LOG_TOPIC;

    @GetMapping()
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> getAllUserReviews() {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received get all user reviews request.");
        List<UserReviewDTO> userReviews = userReviewControllerContract.getAllUserReviews();

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Get all user reviews response returned.");
        return ResponseEntity.ok(RestResponse.of(userReviews));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDTO>> saveUserReview(@RequestBody @Valid UserReviewSaveRequest request) {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received save user review request. Request: " + request);
        UserReviewDTO userReviewDTO = userReviewControllerContract.saveUserReview(request);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Save user review response. Response: " + userReviewDTO);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUserReview(@PathVariable Long id) {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received delete user review request. Request id: " + id);
        userReviewControllerContract.deleteUserReview(id);
    }

    @PatchMapping("/{id}/comment-and-score")
    public ResponseEntity<RestResponse<UserReviewDTO>> updateCommentAndScore(@PathVariable @NotNull Long id,
                                                                             @RequestParam UserReviewUpdateCommentAndScoreRequest request) {

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received update user review command and score request. Request: " + request);
        UserReviewDTO userReviewDTO = userReviewControllerContract.updateCommentAndScore(id, request);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Update user review command and score. Response: " + userReviewDTO);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }
}
