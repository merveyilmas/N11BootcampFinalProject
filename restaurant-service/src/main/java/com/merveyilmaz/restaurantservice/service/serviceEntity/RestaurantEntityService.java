package com.merveyilmaz.restaurantservice.service.serviceEntity;

import com.merveyilmaz.restaurantservice.dao.RestaurantRepository;
import com.merveyilmaz.restaurantservice.entitiy.Restaurant;
import com.merveyilmaz.restaurantservice.exceptions.ItemNotFoundException;
import com.merveyilmaz.restaurantservice.general.GeneralErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantEntityService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant save(Restaurant restaurant) {

        restaurant = restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant findByIdWithControl(String id) {

        Optional<Restaurant> optionalE = restaurantRepository.findById(id);
        Restaurant restaurant;

        if (optionalE.isPresent()) {
            restaurant = optionalE.get();
        } else {
            throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
        }

        return restaurant;
    }

    public Optional<Restaurant> findById(String id){
        return restaurantRepository.findById(id);
    }

    public void delete(String id) {
        restaurantRepository.deleteById(id);
    }
}
