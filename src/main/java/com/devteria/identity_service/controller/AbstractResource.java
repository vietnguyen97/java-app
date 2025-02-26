package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.ApiResponse;

public abstract class AbstractResource {
	public ApiResponse buildResponse(Object entity) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(entity);
		return apiResponse;
	}
}