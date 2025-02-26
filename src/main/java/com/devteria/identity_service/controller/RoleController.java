package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.ApiResponse;
import com.devteria.identity_service.dto.request.RoleRequest;
import com.devteria.identity_service.dto.response.RoleResponse;
import com.devteria.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

@RequestMapping( "/role")
@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController extends AbstractResource {
    RoleService roleService;

    @PostMapping
    ApiResponse create(@RequestBody RoleRequest request) {
        return buildResponse(roleService.create(request));
//        return ApiResponse.<RoleResponse>builder()
//                .data(roleService.create(request))
//                .build();
    }

    @GetMapping
    ApiResponse<Object> getAll() {
        return ApiResponse.builder().data(roleService.getAll()).build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.deleteRole(role);
        return ApiResponse.<Void>builder().build();
    }
}
