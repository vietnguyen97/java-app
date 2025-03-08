package com.devteria.identity_service.service;

import java.util.HashSet;
import java.util.List;
import com.devteria.identity_service.enums.Role;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
	UserRepository useRepository;
	UserMapper userMapper;
	RoleRepository roleRepository;
	PasswordEncoder passwordEncoder;

	public UserResponse createdUser(UserCreatedRequest request) {
		User user = userMapper.toUser(request);
		System.out.println("Request received: " + request);

		HashSet<String> roles = new HashSet<>();
		roles.add(Role.USER.name());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		try {
			user = useRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("User exists");
		}
		return userMapper.toUserResponse(user);
	}

//	hasRole sẽ map đúng giá trị truyền vào + 1 cái prefix là ROLE_
//	hasAuthority sẽ kiểm tra chính xác giá trị mà không thêm prefix ROLE_ mà thay vào là hasAuthority
	@PreAuthorize("hasRole('ADMIN')")
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
		var roles = roleRepository.findAllById(request.getRoles());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles(new HashSet<>(roles));
		return userMapper.toUserResponse(useRepository.save(user));
	}

	public UserResponse getMyInfo() {
		var context = SecurityContextHolder.getContext();
		var name = context.getAuthentication().getName();
		var info = useRepository.findByUsername(name);
		return info.map(userMapper::toUserResponse).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public void deleteUser(String userId) {
		useRepository.deleteById(userId);
	}
}
