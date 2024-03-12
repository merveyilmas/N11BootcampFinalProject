package com.merveyilmaz.userservice.service;

import com.merveyilmaz.userservice.client.RestaurantClient;
import com.merveyilmaz.userservice.dto.RestaurantDTO;
import com.merveyilmaz.userservice.dto.RestaurantWithRateDTO;
import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.service.serviceEntity.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RestaurantRecommendationService {

    private final UserEntityService userEntityService;
    private final RestaurantService restaurantService;

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double R = 6371.01; // Earth's radius in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public List<RestaurantWithRateDTO> recommendRestaurants(long userId) {

        User user = this.userEntityService.findByIdWithControl(userId);
        double userLatitude = user.getLatitude();
        double userLongitude= user.getLongitude();

        List<RestaurantWithRateDTO> restaurantsWithRate = this.restaurantService.getRestaurantsWithRate();
        List<RestaurantWithRateDTO> recommendedRestaurants = new ArrayList<>();

        for (RestaurantWithRateDTO restaurant : restaurantsWithRate) {
            double distance = calculateDistance(userLatitude, userLongitude, restaurant.latitude(), restaurant.longitude());
            double restaurantRate = restaurant.rate();

            if (distance <= 10.0) {
                restaurantRate *= 0.7;
                double distanceScore = (10.0 - distance) / 10.0 * 0.3;

                restaurantRate += distanceScore;
                recommendedRestaurants.add(restaurant);
            }
        }

        Collections.sort(recommendedRestaurants, Comparator.comparingDouble(RestaurantWithRateDTO::rate).reversed());
        int numRecommendations = Math.min(recommendedRestaurants.size(), 3);

        return recommendedRestaurants.subList(0, numRecommendations);
    }

}
