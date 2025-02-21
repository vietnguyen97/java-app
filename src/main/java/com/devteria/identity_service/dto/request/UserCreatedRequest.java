package com.devteria.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedRequest {
	private String username;

	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	private String firstname;
	private String lastname;
	private LocalDate birthday;  // 🚨 Kiểm tra có đúng không?
}
