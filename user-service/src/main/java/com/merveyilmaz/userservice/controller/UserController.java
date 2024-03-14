package com.merveyilmaz.userservice.controller;

import com.merveyilmaz.userservice.controller.concract.UserControllerContract;
import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.general.RestResponse;
import com.merveyilmaz.userservice.request.UserSaveRequest;
import com.merveyilmaz.userservice.request.UserUpdatePasswordRequest;
import com.merveyilmaz.userservice.request.UserUpdateRequest;
import com.merveyilmaz.userservice.response.RestaurantResponse;
import com.merveyilmaz.userservice.service.KafkaProducerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserControllerContract userControllerContract;
    private final KafkaProducerService kafkaProducerService;
    @Value("${kafka-info-log-topic}")
    private String INFO_LOG_TOPIC;


    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves all users")
    public ResponseEntity<RestResponse<List<UserDTO>>> getAllUsers() {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received get all user request.");
        List<UserDTO> users = userControllerContract.getAllUsers();

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Get all users response. Response: " + users);
        return ResponseEntity.ok(RestResponse.of(users));
    }

    @GetMapping("/recommend-restaurant/{userId}")
    @Operation(summary = "Gets the first 3 recommended restaurants by user id")
    public ResponseEntity<RestResponse<List<RestaurantResponse>>> getRecommendRestaurants(@PathVariable @Positive Long userId) {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received get recommend restaurants request. Request: " + userId);
        List<RestaurantResponse> recommendRestaurants = userControllerContract.recommendRestaurant(userId);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Get recommend restaurants response. Response: " + recommendRestaurants);
        return ResponseEntity.ok(RestResponse.of(recommendRestaurants));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody @Valid UserSaveRequest request) {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received save user request. Request: " + request);
        UserDTO userDTO = userControllerContract.saveUser(request);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Save user response. Response: " + userDTO);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @NotNull Long id) {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received delete user request. Request: " + id);
        userControllerContract.deleteUser(id);
    }

    @PutMapping("/{debugUserId}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable @NotNull Long debugUserId, @RequestBody @Valid UserUpdateRequest request) {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received update user request. Request: " + request);
        UserDTO userDTO = userControllerContract.updateUser(request);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Update user response. Response: " + userDTO);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<RestResponse<UserDTO>> updateUserPassword(@PathVariable @NotNull Long id, @RequestBody @Valid UserUpdatePasswordRequest request) {
        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Received update user password request. Request: " + request);
        UserDTO userDTO = userControllerContract.updateUserPassword(id, request);

        kafkaProducerService.sendMessage(INFO_LOG_TOPIC, "Update user password response. Response: " + userDTO);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

}
