package com.merveyilmaz.userservice.service;

import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.response.RestaurantResponse;
import com.merveyilmaz.userservice.service.serviceEntity.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RestaurantRecommendationService {

    private static final double EARTH_RADIUS_KM = 6371.0;
    private final UserEntityService userEntityService;
    private final RestaurantService restaurantService;
    private final KafkaProducerService kafkaProducerService;
    @Value("${kafka-info-log-topic}")
    private String INFO_LOG_TOPIC;

    public List<RestaurantResponse> recommendRestaurants(long userId) {

        User user = this.userEntityService.findByIdWithControl(userId);
        double userLatitude = user.getLatitude();
        double userLongitude = user.getLongitude();

        List<RestaurantResponse> restaurantResponses = this.restaurantService.getRestaurantsWithRate();
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Got restaurants with rate to list recommended restaurants.");
        List<RestaurantResponse> recommendedRestaurants = new ArrayList<>();

        for (RestaurantResponse restaurant : restaurantResponses) {
            double distance = calculateDistance(userLatitude, userLongitude, restaurant.getLatitude(), restaurant.getLongitude());
            double restaurantRate = restaurant.getAverageRate();

            if (distance <= 10.0) {
                double recommendationScore = calculateRecommendationScore(restaurantRate, distance);

                restaurant.setDistance(distance);
                restaurant.setRecommendationScore(recommendationScore);
                recommendedRestaurants.add(restaurant);
            }
        }

        Collections.sort(recommendedRestaurants, Comparator.comparingDouble(RestaurantResponse::getRecommendationScore).reversed());
        int numRecommendations = Math.min(recommendedRestaurants.size(), 3);

        return recommendedRestaurants.subList(0, numRecommendations);
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;

        return distance;
    }

    public static double calculateRecommendationScore(double restaurantRating, double distanceInKm) {
        double ratingWeight = 0.7;
        double distanceWeight = 0.3;

        double reversedDistance = 1.0 / distanceInKm;

        double score = (restaurantRating * ratingWeight) + (reversedDistance * distanceWeight);

        return score;
    }

}
