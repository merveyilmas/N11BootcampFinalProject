package com.merveyilmaz.restaurantservice.request;

import com.merveyilmaz.restaurantservice.enums.EnumGender;
import com.merveyilmaz.restaurantservice.enums.EnumStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserSaveRequest(String name,
                              String surname,
                              String password,
                              LocalDateTime userCreateDate,
                              LocalDate birthDate,
                              String email,
                              double longitude,
                              double latitude,
                              EnumGender gender,
                              EnumStatus status) {
}
