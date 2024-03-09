package com.merveyilmaz.userservice.controller;

import com.merveyilmaz.userservice.controller.concract.UserControllerContract;
import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.general.RestResponse;
import com.merveyilmaz.userservice.request.UserSaveRequest;
import com.merveyilmaz.userservice.request.UserUpdatePasswordRequest;
import com.merveyilmaz.userservice.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
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
