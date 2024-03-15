package com.merveyilmaz.userservice.controller.concract.impl;

import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.dto.UserReviewDTO;
import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.entitiy.UserReview;
import com.merveyilmaz.userservice.enums.EnumGender;
import com.merveyilmaz.userservice.enums.EnumRate;
import com.merveyilmaz.userservice.enums.EnumStatus;
import com.merveyilmaz.userservice.errorMessage.UserErrorMessage;
import com.merveyilmaz.userservice.general.BusinessException;
import com.merveyilmaz.userservice.mapper.UserMapper;
import com.merveyilmaz.userservice.request.*;
import com.merveyilmaz.userservice.service.KafkaProducerService;
import com.merveyilmaz.userservice.service.serviceEntity.UserEntityService;
import com.merveyilmaz.userservice.service.serviceEntity.UserReviewEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static com.merveyilmaz.userservice.enums.EnumRate.ONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerContractImplTest {

    @Mock
    private UserEntityService userEntityService;
    @Mock
    private KafkaProducerService kafkaProducerService;
    @Mock
    private EnumGender gender;
    @Mock
    private EnumStatus status;

    @Value("${kafka-info-log-topic}")
    private String INFO_LOG_TOPIC;

    @Value("${kafka-error-log-topic}")
    private String ERROR_LOG_TOPIC;

    @InjectMocks
    private UserControllerContractImpl userControllerContract;

    @Test
    void shouldGetAllUsers() {
        //given
        Long userId = 1L;
        String name = "merve";
        String surname = "yılmaz";
        String password = "12345";
        LocalDateTime userCreateDate = LocalDateTime.of(1990, Month.MAY, 15, 10, 30);
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String email = "yilmaz.67@outlook.com";
        Double longitude = 123.123;
        Double latitude = 123.123;
        gender = gender.FEMALE;
        status = status.ACTIVE;

        List<User> users = Collections.singletonList(new User());
        users.get(0).setId(userId);
        users.get(0).setName(name);
        users.get(0).setSurname(surname);
        users.get(0).setPassword(password);
        users.get(0).setUserCreateDate(userCreateDate);
        users.get(0).setBirthDate(birthDate);
        users.get(0).setEmail(email);
        users.get(0).setLongitude(longitude);
        users.get(0).setLatitude(latitude);
        users.get(0).setGender(gender);
        users.get(0).setStatus(status);

        List<UserDTO> expectedDTOs = Collections.singletonList(new UserDTO(
                userId, name, surname, password, userCreateDate,
                birthDate, email, longitude, latitude, gender, status
        ));

        //when
        when(userEntityService.findAll()).thenReturn(users);
        List<UserDTO> result = userControllerContract.getAllUsers();

        //then
        verify(userEntityService).findAll();
        verify(kafkaProducerService, times(1)).sendMessage(eq(INFO_LOG_TOPIC), eq("Users listed successfully."));

        assertEquals(expectedDTOs, result);
    }

    @Test
    void recommendRestaurant() {
    }

    @Test
    void shouldSaveUser() {
        //given
        Long userId = 1L;
        String firstName = "merve";
        String lastName = "yılmaz";
        String password = "12345";
        LocalDateTime userCreateDate = LocalDateTime.of(1990, Month.MAY, 15, 10, 30);
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String email = "yilmaz.67@outlook.com";
        Double latitude = 123.123;
        Double longitude = 123.123;
        gender = gender.FEMALE;
        status = status.ACTIVE;

        UserSaveRequest request = new UserSaveRequest(
                firstName, lastName, password, userCreateDate, birthDate,
                email, latitude, longitude, gender, status
        );

        User user = new User(
                userId, firstName, lastName, password, userCreateDate,
                birthDate, email, latitude, longitude, gender, status
        );

        UserDTO expectedDTO = new UserDTO(
                userId, firstName, lastName, password, userCreateDate,
                birthDate, email, latitude, longitude, gender, status
        );

        //when
        when(userEntityService.save(any(User.class))).thenReturn(user);
        UserDTO result = userControllerContract.saveUser(request);

        //then
        verify(userEntityService).save(any(User.class));
        verify(kafkaProducerService, times(1)).sendMessage(eq(INFO_LOG_TOPIC), eq("User saved successfully."));
        assertEquals(expectedDTO, result);
    }

    @Test
    void shouldDeleteUser() {
        //given
        Long id = 1L;

        //when
        userEntityService.delete(id);

        //then
        verify(userEntityService).delete(id);
    }

    @Test
    void updateUser() {
        //given
        Long userId = 1L;
        String firstName = "merve";
        String lastName = "yılmaz";
        String password = "12345";
        LocalDateTime userCreateDate = LocalDateTime.of(1990, Month.MAY, 15, 10, 30);
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String email = "yilmaz.67@outlook.com";
        Double latitude = 123.123;
        Double longitude = 123.123;
        gender = gender.FEMALE;
        status = status.ACTIVE;

        UserUpdateRequest request = new UserUpdateRequest(
                userId, firstName, lastName, password, userCreateDate,
                birthDate, email, latitude, longitude, gender, status
        );

        User user = new User(
                userId, firstName, lastName, password, userCreateDate,
                birthDate, email, latitude, longitude, gender, status
        );

        UserDTO expectedDTO = new UserDTO(
                userId, firstName, lastName, password, userCreateDate,
                birthDate, email, latitude, longitude, gender, status
        );

        //when
        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);
        when(userEntityService.save(user)).thenReturn(user);

        UserDTO updatedUser = userControllerContract.updateUser(request);

        //then
        verify(userEntityService).findByIdWithControl(userId);
        verify(userEntityService).save(user);
        verify(kafkaProducerService).sendMessage(eq(INFO_LOG_TOPIC), eq("User updated successfully."));

        assertEquals(firstName, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(updatedUser, expectedDTO);
    }

    @Test
    void updateUserPassword() {
        //given
        Long userId = 1L;
        String firstName = "merve";
        String lastName = "yılmaz";
        String password = "12345";
        LocalDateTime userCreateDate = LocalDateTime.of(1990, Month.MAY, 15, 10, 30);
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String email = "yilmaz.67@outlook.com";
        Double latitude = 123.123;
        Double longitude = 123.123;
        gender = gender.FEMALE;
        status = status.ACTIVE;

        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String incorrectOldPassword = "incorrectOldPassword";

        UserUpdatePasswordRequest request = new UserUpdatePasswordRequest(incorrectOldPassword, newPassword);

        User user = new User(
                userId, firstName, lastName, password, userCreateDate,
                birthDate, email, latitude, longitude, gender, status
        );

        //when
        when(userEntityService.findByIdWithControl(userId)).thenReturn(user);

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            throw new BusinessException(UserErrorMessage.INVALID_OLD_PASSWORD);
        });

        //then
        assertEquals(UserErrorMessage.INVALID_OLD_PASSWORD, exception.getBaseErrorMessage());
        verify(userEntityService).findByIdWithControl(userId);
        verify(userEntityService, never()).save(user);
        verify(kafkaProducerService, times(1)).sendMessage(eq(ERROR_LOG_TOPIC), eq("Invalid old password!"));
    }
}