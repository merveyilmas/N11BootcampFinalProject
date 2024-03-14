package com.merveyilmaz.userservice.service;

import com.merveyilmaz.userservice.client.RestaurantClient;
import com.merveyilmaz.userservice.converter.RestaurantConverter;
import com.merveyilmaz.userservice.dto.ConvertedRestaurantDTO;
import com.merveyilmaz.userservice.dto.RestaurantDTO;
import com.merveyilmaz.userservice.entitiy.UserReview;
import com.merveyilmaz.userservice.exceptions.EmptyListException;
import com.merveyilmaz.userservice.exceptions.ItemNotFoundException;
import com.merveyilmaz.userservice.general.GeneralErrorMessage;
import com.merveyilmaz.userservice.response.RestaurantResponse;
import com.merveyilmaz.userservice.service.serviceEntity.UserReviewEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantClient restaurantClient;
    private final UserReviewEntityService userReviewEntityService;
    private final KafkaProducerService kafkaProducerService;
    @Value("${kafka-info-log-topic}")
    private String INFO_LOG_TOPIC;
    @Value("${kafka-error-log-topic}")
    private String ERROR_LOG_TOPIC;

    public List<RestaurantResponse> getRestaurantsWithRate() {

        List<RestaurantResponse> restaurantResponses = new ArrayList<>();

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Started get all restaurants request from restaurant client.");
        List<RestaurantDTO> restaurants = this.restaurantClient.getAllRestaurants().getData();
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Get all restaurants response from restaurant client.");

        if(restaurants.isEmpty()){
            kafkaProducerService.sendMessage(ERROR_LOG_TOPIC, "Received empty list from restaurant client!");
            throw new EmptyListException(GeneralErrorMessage.EMPTY_LIST);
        }

        List<ConvertedRestaurantDTO> convertedRestaurants = RestaurantConverter.convertToRestaurants(restaurants);

        for (ConvertedRestaurantDTO restaurant : convertedRestaurants) {

            List<UserReview> userReviewsWithRestaurantId = this.userReviewEntityService.findByRestaurantId((long) restaurant.id());
            double averageRate = calculateAverageRate(userReviewsWithRestaurantId);

            RestaurantResponse restaurantResponse = new RestaurantResponse();
            restaurantResponse.setId(restaurant.id());
            restaurantResponse.setName(restaurant.name());
            restaurantResponse.setLatitude(restaurant.latitude());
            restaurantResponse.setLongitude(restaurant.longitude());
            restaurantResponse.setAverageRate(averageRate);

            restaurantResponses.add(restaurantResponse);
        }

        return restaurantResponses;

    }

    public double calculateAverageRate(List<UserReview> reviews) {

        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double totalRate = 0.0;
        for (UserReview review : reviews) {
            totalRate += review.getRate().getValue();
        }

        return totalRate / reviews.size();
    }

}
