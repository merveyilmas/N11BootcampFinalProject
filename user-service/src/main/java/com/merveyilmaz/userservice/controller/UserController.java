package com.merveyilmaz.userservice.controller;

import com.merveyilmaz.userservice.controller.concract.UserControllerContract;
import com.merveyilmaz.userservice.dto.RestaurantWithRateDTO;
import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.general.RestResponse;
import com.merveyilmaz.userservice.request.UserSaveRequest;
import com.merveyilmaz.userservice.request.UserUpdatePasswordRequest;
import com.merveyilmaz.userservice.request.UserUpdateRequest;
import com.merveyilmaz.userservice.service.KafkaProducerService;
import com.merveyilmaz.userservice.service.serviceEntity.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
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
    @GetMapping()
    public ResponseEntity<RestResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userControllerContract.getAllUsers();
        return ResponseEntity.ok(RestResponse.of(users));
    }

    @GetMapping("/{id}/recommend-restaurant")
    public ResponseEntity<RestResponse<List<RestaurantWithRateDTO>>> getRecommendRestaurants(@PathVariable Long userId) {
        List<RestaurantWithRateDTO> recommendRestaurants = userControllerContract.recommendRestaurant(userId);
        return ResponseEntity.ok(RestResponse.of(recommendRestaurants));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody UserSaveRequest request) {
        UserDTO userDTO = userControllerContract.saveUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userControllerContract.deleteUser(id);
    }

    @PutMapping("/{debugUserId}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable Long debugUserId, @RequestBody UserUpdateRequest request) {
        UserDTO userDTO = userControllerContract.updateUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<RestResponse<UserDTO>> updateUserPassword(@PathVariable Long id, @RequestBody UserUpdatePasswordRequest request) {
        UserDTO userDTO = userControllerContract.updateUserPassword(id, request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

}
