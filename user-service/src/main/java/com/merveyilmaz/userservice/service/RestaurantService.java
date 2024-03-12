package com.merveyilmaz.userservice.service;

import com.merveyilmaz.userservice.client.RestaurantClient;
import com.merveyilmaz.userservice.dto.RestaurantDTO;
import com.merveyilmaz.userservice.dto.RestaurantWithRateDTO;
import com.merveyilmaz.userservice.entitiy.UserReview;
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


    public List<RestaurantWithRateDTO> getRestaurantsWithRate() {

        List<RestaurantWithRateDTO> restaurantsWithRate = new ArrayList<>();
        List<RestaurantDTO> restaurants = this.restaurantClient.getAllRestaurants().getData();

        for (RestaurantDTO restaurant : restaurants) {

            List<UserReview> userReviewsWithRestaurantId = this.userReviewEntityService.findByRestaurantId((long) restaurant.id());
            double averageRate = calculateAverageRate(userReviewsWithRestaurantId);

            RestaurantWithRateDTO restaurantWithRate = new RestaurantWithRateDTO(restaurant.id(),
                    restaurant.name(),
                    restaurant.latitude(),
                    restaurant.longitude(),
                    averageRate);

            restaurantsWithRate.add(restaurantWithRate);
        }
        return restaurantsWithRate;

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
