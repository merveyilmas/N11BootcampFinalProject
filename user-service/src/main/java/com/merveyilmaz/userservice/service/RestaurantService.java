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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantClient restaurantClient;
    private final UserReviewEntityService userReviewEntityService;


    public List<RestaurantResponse> getRestaurantsWithRate() {

        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        List<RestaurantDTO> restaurants = this.restaurantClient.getAllRestaurants().getData();

        if(restaurants.isEmpty()){
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
