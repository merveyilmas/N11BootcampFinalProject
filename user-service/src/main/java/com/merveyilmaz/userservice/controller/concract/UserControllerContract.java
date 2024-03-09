package com.merveyilmaz.userservice.controller.concract;

import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.general.RestResponse;
import com.merveyilmaz.userservice.request.UserSaveRequest;
import com.merveyilmaz.userservice.request.UserUpdatePasswordRequest;
import com.merveyilmaz.userservice.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserControllerContract {

    UserDTO saveUser(UserSaveRequest request);
    void deleteUser(Long id);
    UserDTO updateUser(UserUpdateRequest request);
    UserDTO updateUserPassword(@PathVariable Long id, @RequestBody UserUpdatePasswordRequest request);
}
