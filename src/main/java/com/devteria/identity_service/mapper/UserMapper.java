package com.devteria.identity_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.devteria.identity_service.dto.request.UserCreatedRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "roles", ignore = true)
	User toUser(UserCreatedRequest request);

	UserResponse toUserResponse(User user);

	@Mapping(target = "roles", ignore = true)
	void updateUser(@MappingTarget User user, UserCreatedRequest request);
}

