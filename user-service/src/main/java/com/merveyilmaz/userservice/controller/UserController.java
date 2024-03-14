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

    @GetMapping("/errorLog")
    public ResponseEntity<RestResponse<UserDTO>> testErrorLogKafka() {
        kafkaProducerService.sendMessage("errorLog", "error");
        return null;
    }

    @GetMapping("/infoLog")
    public ResponseEntity<RestResponse<UserDTO>> testInfoLogKafka() {
        kafkaProducerService.sendMessage("infoLog", "info");
        return null;
    }
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves all users")
    public ResponseEntity<RestResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userControllerContract.getAllUsers();
        return ResponseEntity.ok(RestResponse.of(users));
    }

    @GetMapping("/recommend-restaurant/{userId}")
    @Operation(summary = "Gets the first 3 recommended restaurants by user id")
    public ResponseEntity<RestResponse<List<RestaurantResponse>>> getRecommendRestaurants(@PathVariable @Positive Long userId) {
        List<RestaurantResponse> recommendRestaurants = userControllerContract.recommendRestaurant(userId);
        return ResponseEntity.ok(RestResponse.of(recommendRestaurants));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody @Valid UserSaveRequest request) {
        UserDTO userDTO = userControllerContract.saveUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @NotNull Long id) {
        userControllerContract.deleteUser(id);
    }

    @PutMapping("/{debugUserId}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable @NotNull Long debugUserId, @RequestBody @Valid UserUpdateRequest request) {
        UserDTO userDTO = userControllerContract.updateUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<RestResponse<UserDTO>> updateUserPassword(@PathVariable @NotNull Long id, @RequestBody @Valid UserUpdatePasswordRequest request) {
        UserDTO userDTO = userControllerContract.updateUserPassword(id, request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

}
