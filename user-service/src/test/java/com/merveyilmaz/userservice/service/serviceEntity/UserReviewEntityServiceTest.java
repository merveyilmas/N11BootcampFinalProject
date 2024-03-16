package com.merveyilmaz.userservice.service.serviceEntity;

import com.merveyilmaz.userservice.dao.UserReviewRepository;
import com.merveyilmaz.userservice.entitiy.UserReview;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserReviewEntityServiceTest {

    @Mock
    private UserReviewRepository userReviewRepository;
    @InjectMocks
    private UserReviewEntityService userReviewEntityService;

    @Test
    void shouldFindByRestaurantId() {
        //given
        Long restaurantId = 1L;
        List<UserReview> expectedReviews = new ArrayList<>();
        expectedReviews.add(new UserReview());
        expectedReviews.add(new UserReview());

        //when
        when(userReviewRepository.findByRestaurantId(restaurantId)).thenReturn(expectedReviews);

        List<UserReview> actualReviews = userReviewEntityService.findByRestaurantId(restaurantId);

        //then
        verify(userReviewRepository).findByRestaurantId(restaurantId);

        assertEquals(expectedReviews.size(), actualReviews.size());
        for (int i = 0; i < expectedReviews.size(); i++) {
            assertEquals(expectedReviews.get(i), actualReviews.get(i));
        }

    }
}