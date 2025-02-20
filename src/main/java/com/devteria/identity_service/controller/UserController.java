package com.devteria.identity_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devteria.identity_service.dto.request.ApiResponse;
import com.devteria.identity_service.dto.request.UserCreatedRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService useService;

	@PostMapping
	public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreatedRequest request) {
		ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
		apiResponse.setData(useService.createdUser(request));
		return apiResponse;
	}

	@GetMapping
	public ApiResponse<List<User>> getUsers() {
		ApiResponse<List<User>> apiResponse = new ApiResponse<>();
		apiResponse.setData(useService.getUsers());
		return apiResponse;
	}

	@GetMapping("/{userId}")
	public ApiResponse<UserResponse> getOneUser(@PathVariable("userId") String userId) {
		ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
		apiResponse.setData(useService.getUser(userId));
		return apiResponse;
	}

	@PutMapping("/{userId}")
	public UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserCreatedRequest request) {
		return useService.updateUser(userId, request);
	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable("userId") String userId) {
		useService.deleteUser(userId);
		return "User has been deleted";
	}
}
