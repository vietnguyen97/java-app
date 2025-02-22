package com.devteria.identity_service.service;

import java.util.HashSet;
import java.util.List;

import com.devteria.identity_service.enums.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devteria.identity_service.dto.request.UserCreatedRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.mapper.UserMapper;
import com.devteria.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
	UserRepository useRepository;
	UserMapper userMapper;
	PasswordEncoder passwordEncoder;

	public UserResponse createdUser(UserCreatedRequest request) {
		if (useRepository.existsByUsername(request.getUsername()))
			throw new RuntimeException("User existed");

		User user = userMapper.toUser(request);
		System.out.println("Request received: " + request);

		HashSet<String> roles = new HashSet<>();
		roles.add(Role.USER.name());
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user = useRepository.save(user);
		return userMapper.toUserResponse(user);
	}

	public List<User> getUsers() {
		return useRepository.findAll();
	}

	public UserResponse getUser(String id) {
		return userMapper
				.toUserResponse(useRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
	}

	public UserResponse updateUser(String userId, UserCreatedRequest request) {
		User user = useRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		userMapper.updateUser(user, request);
		return userMapper.toUserResponse(useRepository.save(user));
	}

	public void deleteUser(String userId) {
		useRepository.deleteById(userId);
	}
}
