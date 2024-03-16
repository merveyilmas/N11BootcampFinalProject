package com.merveyilmaz.userservice.service;

import com.merveyilmaz.userservice.client.RestaurantClient;
import com.merveyilmaz.userservice.dto.RestaurantDTO;
import com.merveyilmaz.userservice.entitiy.UserReview;
import com.merveyilmaz.userservice.enums.EnumRate;
import com.merveyilmaz.userservice.exceptions.EmptyListException;
import com.merveyilmaz.userservice.exceptions.NullPointerException;
import com.merveyilmaz.userservice.general.RestResponse;
import com.merveyilmaz.userservice.request.UserReviewSaveRequest;
import com.merveyilmaz.userservice.response.RestaurantResponse;
import com.merveyilmaz.userservice.service.serviceEntity.UserReviewEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantClient restaurantClient;
    @Mock
    private UserReviewEntityService userReviewEntityService;
    @Mock
    private KafkaProducerService kafkaProducerService;
    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void shouldGetRestaurantsWithRate() {
        //given
        Long userId = 1L;
        Long restaurantId = 2L;
        String comment = "comment";
        Long reviewId = 5L;
        EnumRate rateValue = EnumRate.FIVE;

        List<RestaurantDTO> restaurants = new ArrayList<>();
        restaurants.add(new RestaurantDTO(1, "Restaurant A", "13.03.2024",407128, -740060));
        restaurants.add(new RestaurantDTO(2, "Restaurant B", "13.03.2024",340522, -1182437));

        List<UserReview> userReviews = new ArrayList<>();
        userReviews.add(new UserReview(reviewId, userId, restaurantId, rateValue, comment));

        RestResponse<List<RestaurantDTO>> realResponse = restaurantClient.getAllRestaurants();

        //when
        when(restaurantClient.getAllRestaurants()).thenReturn(realResponse);
        when(userReviewEntityService.findByRestaurantId(anyLong())).thenReturn(userReviews);

        List<RestaurantResponse> restaurantResponses = restaurantService.getRestaurantsWithRate();

        //then
        assertNotNull(restaurantResponses);
        assertEquals(2, restaurantResponses.size());
        assertEquals(5.0, restaurantResponses.get(0).getAverageRate()); // Average rate for Restaurant A
        assertEquals(0.0, restaurantResponses.get(1).getAverageRate()); // No reviews for Restaurant B
    }

    @Test
    public void shouldGetRestaurantsWithRate_NullPointerException() {
        //given
        RestResponse<List<RestaurantDTO>> realResponse = restaurantClient.getAllRestaurants();

        //when
        when(restaurantClient.getAllRestaurants()).thenReturn(realResponse);

        //then
        assertThrows(NullPointerException.class, () -> {
            restaurantService.getRestaurantsWithRate();
        });
    }

    @Test
    public void shouldCalculateAverageRate() {
        //given
        Long reviewId = 1L;
        Long userId = 1L;
        Long restaurantId = 1L;
        EnumRate rateValue = EnumRate.FOUR;
        String comment = "comment";

        List<UserReview> userReviews = Collections.singletonList(new UserReview());
        userReviews.get(0).setId(reviewId);
        userReviews.get(0).setUserId(userId);
        userReviews.get(0).setRestaurantId(restaurantId);
        userReviews.get(0).setRate(rateValue);
        userReviews.get(0).setComment(comment);

        //when
        double averageRate = restaurantService.calculateAverageRate(userReviews);

        //then
        assertEquals(4.0, averageRate);
    }

    @Test
    public void shouldCalculateAverageRate_EmptyList() {
        //given
        List<UserReview> emptyList = new ArrayList<>();

        //when
        double averageRate = restaurantService.calculateAverageRate(emptyList);

        //then
        assertEquals(0.0, averageRate);
    }

}