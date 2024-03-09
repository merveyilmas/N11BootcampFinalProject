package com.merveyilmaz.userservice.controller.concract.impl;

import com.merveyilmaz.userservice.client.RestaurantClient;
import com.merveyilmaz.userservice.dto.UserReviewDTO;
import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.entitiy.UserReview;
import com.merveyilmaz.userservice.enums.EnumRate;
import com.merveyilmaz.userservice.enums.EnumStatus;
import com.merveyilmaz.userservice.request.UserReviewSaveRequest;
import com.merveyilmaz.userservice.request.UserReviewUpdateCommentAndScoreRequest;
import com.merveyilmaz.userservice.service.serviceEntity.UserReviewEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserReviewControllerContractImplTest {

    @Mock
    private UserReviewEntityService userReviewEntityService;
    @Mock
    private RestaurantClient restaurantClient;
    @Mock
    private EnumRate rate;
    @Mock
    private EnumStatus status;

    @InjectMocks
    private UserReviewControllerContractImpl userReviewControllerContractImpl;

    @Test
    void shouldSave() {
        //given
        UserReviewSaveRequest request = new UserReviewSaveRequest(1L, 2L, rate.FIVE, "comment");
        UserReview userReview = new UserReview(5L, 1L, 2L, rate.TWO, "comment");

        User user = new User();
        user.setId(1L);
        user.setName("merve");
        user.setSurname("yılmaz");

        UserReviewDTO expectedDTO = new UserReviewDTO(5L, 1L, 2L, rate.TWO, "comment");


        //when
        when(userReviewEntityService.save(any(UserReview.class))).thenReturn(userReview);
        UserReviewDTO result = userReviewControllerContractImpl.saveUserReview(request);

        //then
        verify(userReviewEntityService).save(any(UserReview.class));
        assertEquals(expectedDTO, result);
    }

    @Test
    void shouldUpdateCommentAndScore() {
        //given
        Long id = 1L;
        String newComment = "New comment";
        EnumRate newScore = EnumRate.ONE;

        UserReviewUpdateCommentAndScoreRequest request = new UserReviewUpdateCommentAndScoreRequest(newComment, newScore);

        UserReview userReview = new UserReview();
        userReview.setId(id);
        userReview.setComment(newComment);
        userReview.setRate(newScore);
        userReview.setUserId(2L);
        userReview.setRestaurantId(4L);

        UserReviewDTO expectedDTO = new UserReviewDTO(1L, 2L, 4L, newScore, newComment);

        //when
        when(userReviewEntityService.findByIdWithControl(id)).thenReturn(userReview);
        when(userReviewEntityService.save(userReview)).thenReturn(userReview);

        UserReviewDTO result = userReviewControllerContractImpl.updateCommentAndScore(id, request);

        //then
        verify(userReviewEntityService).findByIdWithControl(id);
        verify(userReviewEntityService).save(any(UserReview.class));

        assertEquals(expectedDTO, result);
    }

}
