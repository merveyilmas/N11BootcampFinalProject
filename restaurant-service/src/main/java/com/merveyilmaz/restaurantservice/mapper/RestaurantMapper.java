package com.merveyilmaz.restaurantservice.mapper;

import com.merveyilmaz.restaurantservice.dto.RestaurantDTO;
import com.merveyilmaz.restaurantservice.entitiy.Restaurant;
import com.merveyilmaz.restaurantservice.request.RestaurantSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    Restaurant convertToRestaurant(RestaurantSaveRequest request);
    RestaurantDTO convertToRestaurantDTO(Restaurant restaurant);
    @Mapping(target = "id", ignore = true)
    void updateUserFields(@MappingTarget Restaurant restaurant, RestaurantSaveRequest request);
}
