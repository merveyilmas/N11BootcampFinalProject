package com.merveyilmaz.userservice.controller.concract;

import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.request.UserSaveRequest;
import com.merveyilmaz.userservice.request.UserUpdatePasswordRequest;
import com.merveyilmaz.userservice.request.UserUpdateRequest;
import com.merveyilmaz.userservice.response.RestaurantResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserControllerContract {

    List<UserDTO> getAllUsers();
    List<RestaurantResponse> recommendRestaurant(Long userId);
    UserDTO saveUser(UserSaveRequest request);
    void deleteUser(Long id);
    UserDTO updateUser(UserUpdateRequest request);
    UserDTO updateUserPassword(@PathVariable Long id, @RequestBody UserUpdatePasswordRequest request);
}
