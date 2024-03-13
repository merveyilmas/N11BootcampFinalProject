package com.merveyilmaz.restaurantservice.mapper;

import com.merveyilmaz.restaurantservice.dto.RestaurantDTO;
import com.merveyilmaz.restaurantservice.entitiy.Restaurant;
import com.merveyilmaz.restaurantservice.request.RestaurantSaveRequest;
import com.merveyilmaz.restaurantservice.request.RestaurantUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    Restaurant convertToRestaurant(RestaurantSaveRequest request);
    RestaurantDTO convertToRestaurantDTO(Restaurant restaurant);
    List<RestaurantDTO> convertToRestaurantDTOs(List<Restaurant> restaurants);
    @Mapping(target = "id", ignore = true)
    void updateUserFields(@MappingTarget Restaurant restaurant, RestaurantUpdateRequest request);
}
