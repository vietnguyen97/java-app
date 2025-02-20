package com.devteria.identity_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.devteria.identity_service.dto.request.UserCreatedRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	User toUser(UserCreatedRequest request);

	UserResponse toUserResponse(User user);

	void updateUser(@MappingTarget User user, UserCreatedRequest request);
}
