package com.merveyilmaz.restaurantservice.dao;

import com.merveyilmaz.restaurantservice.entitiy.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {
}
