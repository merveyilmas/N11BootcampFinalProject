package com.merveyilmaz.userservice.dto;

import com.merveyilmaz.userservice.enums.EnumGender;
import com.merveyilmaz.userservice.enums.EnumStatus;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDTO(Long id,
                      String name,
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
