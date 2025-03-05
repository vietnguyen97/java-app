package com.devteria.identity_service.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
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

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends AbstractResource {
	@Autowired
	private UserService useService;

	@PostMapping
	public ApiResponse createUser(@RequestBody @Valid UserCreatedRequest request) {
		return buildResponse(useService.createdUser(request));
	}

	@GetMapping
	public ApiResponse getUsers() {
		return buildResponse(useService.getUsers());
	}

	@GetMapping("/{userId}")
	public ApiResponse getOneUser(@PathVariable("userId") String userId) {
		return buildResponse(useService.getUser(userId));
	}

	@PutMapping("/{userId}")
	public ApiResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserCreatedRequest request) {
		return buildResponse(useService.updateUser(userId, request));
	}

	@GetMapping("/my-info")
	public ApiResponse getMyInfo() {
		return buildResponse(useService.getMyInfo());
	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable("userId") String userId) {
		useService.deleteUser(userId);
		return "User has been deleted";
	}
}
