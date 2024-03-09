package com.merveyilmaz.restaurantservice.entitiy;

import com.merveyilmaz.restaurantservice.enums.EnumGender;
import com.merveyilmaz.restaurantservice.enums.EnumStatus;
import com.merveyilmaz.restaurantservice.general.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Restaurant extends BaseEntity {

    private Long id;
    private String name;
    private LocalDateTime restaurantCreateDate;
    private double longitude;
    private double latitude;

}
