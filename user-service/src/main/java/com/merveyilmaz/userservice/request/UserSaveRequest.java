package com.merveyilmaz.userservice.request;

import com.merveyilmaz.userservice.enums.EnumGender;
import com.merveyilmaz.userservice.enums.EnumStatus;

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
