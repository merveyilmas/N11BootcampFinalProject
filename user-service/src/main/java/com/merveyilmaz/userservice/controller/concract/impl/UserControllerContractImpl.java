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
import com.merveyilmaz.userservice.service.serviceEntity.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService userEntityService;

    @Override
    public UserDTO saveUser(UserSaveRequest request) {
        User user = UserMapper.INSTANCE.convertToUser(request);

        user = userEntityService.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userEntityService.delete(id);
    }

    @Override
    public UserDTO updateUser(UserUpdateRequest request) {
        User user = userEntityService.findByIdWithControl(request.id());
        UserMapper.INSTANCE.updateUserFields(user, request);

        userEntityService.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUserPassword(Long id, UserUpdatePasswordRequest request) {
        User user = userEntityService.findByIdWithControl(id);

        if (!user.getPassword().equals(request.oldPass())) {
            throw new BusinessException(UserErrorMessage.INVALID_OLD_PASSWORD);
        }

        user.setPassword(request.newPass());
        userEntityService.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }
}
