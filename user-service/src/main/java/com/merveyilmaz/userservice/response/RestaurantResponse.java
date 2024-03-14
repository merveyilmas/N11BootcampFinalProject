package com.merveyilmaz.userservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private double distance;
    private double averageRate;
    private double recommendationScore;
}
