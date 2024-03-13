package com.merveyilmaz.userservice.mapper;

import com.merveyilmaz.userservice.dto.UserDTO;
import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.request.UserSaveRequest;
import com.merveyilmaz.userservice.request.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User convertToUser(UserSaveRequest request);
    UserDTO convertToUserDTO(User user);
    List<UserDTO> convertToUserDTOs(List<User> users);
    @Mapping(target = "id", ignore = true)
    void updateUserFields(@MappingTarget User user, UserUpdateRequest request);
}
