package com.devteria.identity_service.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreatedRequest {
	String username;

	@Size(min = 8, message = "Password must be at least 8 characters")
	String password;
	String firstname;
	String lastname;
	LocalDate birthday;

}
