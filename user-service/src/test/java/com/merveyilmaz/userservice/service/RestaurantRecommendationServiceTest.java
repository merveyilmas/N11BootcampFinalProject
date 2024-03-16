package com.merveyilmaz.userservice.service;

import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.response.RestaurantResponse;
import com.merveyilmaz.userservice.service.serviceEntity.UserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantRecommendationServiceTest {

    @Mock
    private UserEntityService userEntityService;
    @Mock
    private RestaurantService restaurantService;
    @Mock
    private KafkaProducerService kafkaProducerService;
    @InjectMocks
    private RestaurantRecommendationService restaurantRecommendationService;

    @Test
    void shouldRecommendRestaurants() {
        //given
        long userId = 2L;

        User user = new User();
        user.setLatitude(40.7128); // New York latitude
        user.setLongitude(-74.0060); // New York longitude

        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        restaurantResponses.add(new RestaurantResponse(1, "Restaurant 1", 40.7128, -74.0060, 4.5, 3, 3)); // New York coordinates
        restaurantResponses.add(new RestaurantResponse(2, "Restaurant 2", 34.0522, -118.2437, 4.5, 2, 2)); // Los Angeles coordinates
        restaurantResponses.add(new RestaurantResponse(3, "Restaurant 3", 41.8781, -87.6298, 4.5, 4,4)); // Chicago coordinates

        //when
        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);
        when(restaurantService.getRestaurantsWithRate()).thenReturn(restaurantResponses);

        List<RestaurantResponse> recommendedRestaurants = restaurantRecommendationService.recommendRestaurants(userId);

        //then
        assertEquals("Restaurant 1", recommendedRestaurants.get(0).getName()); // Highest recommendation score
    }

    @Test
    void shouldCalculateDistance() {
        //given
        double lat1 = 40.7128; // New York latitude
        double lon1 = -74.0060; // New York longitude
        double lat2 = 34.0522; // Los Angeles latitude
        double lon2 = -118.2437; // Los Angeles longitude
        double expectedDistance = 3935.746254609722;

        //when
        double calculatedDistance = RestaurantRecommendationService.calculateDistance(lat1, lon1, lat2, lon2);

        //then
        assertEquals(expectedDistance, calculatedDistance, 0.01);
    }

}