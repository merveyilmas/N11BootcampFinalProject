package com.merveyilmaz.userservice.controller.concract.impl;

import com.merveyilmaz.userservice.controller.concract.UserControllerContract;
import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.errorMessage.UserErrorMessage;
import com.merveyilmaz.userservice.general.BusinessException;
import com.merveyilmaz.userservice.mapper.UserMapper;
import com.merveyilmaz.userservice.request.UserSaveRequest;
import com.merveyilmaz.userservice.request.UserUpdatePasswordRequest;
import com.merveyilmaz.userservice.request.UserUpdateRequest;
import com.merveyilmaz.userservice.response.RestaurantResponse;
import com.merveyilmaz.userservice.service.KafkaProducerService;
import com.merveyilmaz.userservice.service.RestaurantRecommendationService;
import com.merveyilmaz.userservice.service.serviceEntity.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService userEntityService;
    private final RestaurantRecommendationService recommendationService;

    private final KafkaProducerService kafkaProducerService;
    @Value("${kafka-info-log-topic}")
    private String INFO_LOG_TOPIC;
    @Value("${kafka-error-log-topic}")
    private String ERROR_LOG_TOPIC;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userEntityService.findAll();

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Users listed successfully.");
        return UserMapper.INSTANCE.convertToUserDTOs(users);
    }

    @Override
    public List<RestaurantResponse> recommendRestaurant(Long userId) {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Restaurants recommended successfully.");
        return recommendationService.recommendRestaurants(userId);
    }

    @Override
    public UserDTO saveUser(UserSaveRequest request) {
        User user = UserMapper.INSTANCE.convertToUser(request);

        user = userEntityService.save(user);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "User saved successfully.");
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userEntityService.delete(id);
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "User deleted successfully.");
    }

    @Override
    public UserDTO updateUser(UserUpdateRequest request) {
        User user = userEntityService.findByIdWithControl(request.id());
        UserMapper.INSTANCE.updateUserFields(user, request);

        userEntityService.save(user);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "User updated successfully.");
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUserPassword(Long id, UserUpdatePasswordRequest request) {
        User user = userEntityService.findByIdWithControl(id);

        if (!user.getPassword().equals(request.oldPass())) {
            kafkaProducerService.sendMessage(ERROR_LOG_TOPIC, "Invalid old password!");
            throw new BusinessException(UserErrorMessage.INVALID_OLD_PASSWORD);
        }

        user.setPassword(request.newPass());
        userEntityService.save(user);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "User password updated successfully.");
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }
}
