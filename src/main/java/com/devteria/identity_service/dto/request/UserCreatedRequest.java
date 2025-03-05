package com.devteria.identity_service.dto.request;

import com.devteria.identity_service.validator.BirthConstraints;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreatedRequest {
	@Size(min = 4, message = "USERNAME_INVALID")
	String username;

	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	private String firstname;
	private String lastname;

	@BirthConstraints(min = 18, message = "INVALID_DOB")
	private LocalDate birthday;

	List<String> roles;
}
