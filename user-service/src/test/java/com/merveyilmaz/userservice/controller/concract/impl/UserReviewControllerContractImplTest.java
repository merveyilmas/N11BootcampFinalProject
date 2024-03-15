package com.merveyilmaz.userservice.controller.concract.impl;

import com.merveyilmaz.userservice.client.RestaurantClient;
import com.merveyilmaz.userservice.dto.UserReviewDTO;
import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.entitiy.UserReview;
import com.merveyilmaz.userservice.enums.EnumRate;
import com.merveyilmaz.userservice.enums.EnumStatus;
import com.merveyilmaz.userservice.request.UserReviewSaveRequest;
import com.merveyilmaz.userservice.request.UserReviewUpdateCommentAndScoreRequest;
import com.merveyilmaz.userservice.service.KafkaProducerService;
import com.merveyilmaz.userservice.service.serviceEntity.UserReviewEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;
import java.util.List;

import static com.merveyilmaz.userservice.enums.EnumRate.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserReviewControllerContractImplTest {

    @Mock
    private UserReviewEntityService userReviewEntityService;
    @Mock
    private KafkaProducerService kafkaProducerService;
    @Mock
    private EnumRate rate;

    @Value("${kafka-info-log-topic}")
    private String INFO_LOG_TOPIC;

    @Value("${kafka-error-log-topic}")
    private String ERROR_LOG_TOPIC;

    @InjectMocks
    private UserReviewControllerContractImpl userReviewControllerContractImpl;

    @Test
    void shouldGetAllUserReviews() {
        //given
        Long reviewId = 1L;
        Long userId = 1L;
        Long restaurantId = 1L;
        EnumRate rateValue = EnumRate.ONE;
        String comment = "comment";

        List<UserReview> userReviews = Collections.singletonList(new UserReview());
        userReviews.get(0).setId(reviewId);
        userReviews.get(0).setUserId(userId);
        userReviews.get(0).setRestaurantId(restaurantId);
        userReviews.get(0).setRate(rateValue);
        userReviews.get(0).setComment(comment);

        List<UserReviewDTO> expectedDTOs = Collections.singletonList(new UserReviewDTO(
                reviewId, userId, restaurantId, rateValue, comment
        ));

        //when
        when(userReviewEntityService.findAll()).thenReturn(userReviews);
        List<UserReviewDTO> result = userReviewControllerContractImpl.getAllUserReviews();

        //then
        verify(userReviewEntityService).findAll();
        verify(kafkaProducerService, times(1)).sendMessage(eq(INFO_LOG_TOPIC), eq("User reviews listed successfully."));

        assertEquals(expectedDTOs, result);
    }
    @Test
    void shouldSave() {
        //given
        Long userId = 1L;
        Long restaurantId = 2L;
        EnumRate rateValue = EnumRate.FIVE;
        String comment = "comment";
        Long reviewId = 5L;
        EnumRate rateValue2 = EnumRate.TWO;

        UserReviewSaveRequest request = new UserReviewSaveRequest(userId, restaurantId, rateValue, comment);
        UserReview userReview = new UserReview(reviewId, userId, restaurantId, rateValue2, comment);

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
        verify(kafkaProducerService, times(1)).sendMessage(eq(INFO_LOG_TOPIC), eq("User reviewed saved successfully."));
        assertEquals(expectedDTO, result);
    }

    @Test
    void deleteUserReview() {
        //given
        Long id = 1L;

        //when
        userReviewEntityService.delete(id);

        //then
        verify(userReviewEntityService).delete(id);
    }

    @Test
    void shouldUpdateCommentAndScore() {
        //given
        Long id = 1L;
        String newComment = "New comment";
        EnumRate newScore = ONE;

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
        verify(kafkaProducerService, times(1)).sendMessage(eq(INFO_LOG_TOPIC), eq("User review comment and score updated successfully."));

        assertEquals(expectedDTO, result);
    }

}
