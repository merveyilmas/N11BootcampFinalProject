package com.merveyilmaz.restaurantservice.entitiy;


public class Restaurant {

    private String id;
    private String name;
    private String restaurantCreateDate;
    private int longitude;
    private int latitude;

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

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
