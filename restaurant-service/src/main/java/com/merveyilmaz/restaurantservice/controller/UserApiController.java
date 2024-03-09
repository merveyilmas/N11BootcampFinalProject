package com.merveyilmaz.restaurantservice.controller;

import com.merveyilmaz.restaurantservice.client.UserClient;
import com.merveyilmaz.restaurantservice.dto.UserDTO;
import com.merveyilmaz.restaurantservice.general.RestResponse;
import com.merveyilmaz.restaurantservice.request.UserSaveRequest;
import com.merveyilmaz.restaurantservice.request.UserUpdatePasswordRequest;
import com.merveyilmaz.restaurantservice.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {

    private UserClient userClient;

    public UserApiController(UserClient userClient) {
        this.userClient = userClient;
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody UserSaveRequest request) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

    }

    @PutMapping("/{debugUserId}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable Long debugUserId, @RequestBody UserUpdateRequest request) {
       return null;
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<RestResponse<UserDTO>> updateUserPassword(@PathVariable Long id, @RequestBody UserUpdatePasswordRequest request) {
        return null;
    }

}
