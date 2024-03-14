package com.merveyilmaz.userservice.request;

import com.merveyilmaz.userservice.enums.EnumGender;
import com.merveyilmaz.userservice.enums.EnumStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserSaveRequest(@NotBlank String name,
                              @NotBlank String surname,
                              @NotBlank String password,
                              LocalDateTime userCreateDate,
                              LocalDate birthDate,
                              @Email String email,
                              @NotNull double longitude,
                              @NotNull double latitude,
                              EnumGender gender,
                              EnumStatus status) {
}
