package com.merveyilmaz.restaurantservice.entitiy;


public class Restaurant {

    private String id;
    private String name;
    private String restaurantCreateDate;
    private double longitude;
    private double latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantCreateDate() {
        return restaurantCreateDate;
    }

    public void setRestaurantCreateDate(String restaurantCreateDate) {
        this.restaurantCreateDate = restaurantCreateDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
