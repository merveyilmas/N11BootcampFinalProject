package com.merveyilmaz.userservice.controller.concract.impl;

import com.merveyilmaz.userservice.client.RestaurantClient;
import com.merveyilmaz.userservice.controller.concract.UserReviewControllerContract;
import com.merveyilmaz.userservice.dto.UserReviewDTO;
import com.merveyilmaz.userservice.entitiy.UserReview;
import com.merveyilmaz.userservice.mapper.UserReviewMapper;
import com.merveyilmaz.userservice.request.UserReviewSaveRequest;
import com.merveyilmaz.userservice.request.UserReviewUpdateCommentAndScoreRequest;
import com.merveyilmaz.userservice.service.serviceEntity.UserReviewEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserReviewControllerContractImpl implements UserReviewControllerContract {

    private final UserReviewEntityService userReviewEntityService;
    private final RestaurantClient restaurantClient;

    @Override
    public UserReviewDTO saveUserReview(UserReviewSaveRequest request) {
        UserReview userReview = UserReviewMapper.INSTANCE.convertToUserReview(request);

        userReview = userReviewEntityService.save(userReview);

        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public void deleteUserReview(Long id) {
        userReviewEntityService.delete(id);
    }

    @Override
    public UserReviewDTO updateCommentAndScore(Long id, UserReviewUpdateCommentAndScoreRequest newCommentAndScore) {
        UserReview userReview = userReviewEntityService.findByIdWithControl(id);

        userReview.setComment(newCommentAndScore.newComment());
        userReview.setRate(newCommentAndScore.newScore());
        userReviewEntityService.save(userReview);

        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }
}
