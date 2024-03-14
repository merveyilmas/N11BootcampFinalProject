package com.merveyilmaz.restaurantservice.entitiy;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SolrDocument(solrCoreName = "restaurant_service")
public class Restaurant {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;
    @NotBlank
    @Indexed(name = "name", type = "string")
    private String name;
    @NotBlank
    @Indexed(name = "restaurant_create_date", type = "pdate")
    private String restaurantCreateDate;
    @NotNull
    @Indexed(name = "longitude", type = "pint")
    private int longitude;
    @NotNull
    @Indexed(name = "latitude", type = "pint")
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

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}
