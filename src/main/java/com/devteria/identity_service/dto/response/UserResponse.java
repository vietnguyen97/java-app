package com.devteria.identity_service.dto.response;

import java.time.LocalDate;
import java.util.Set;
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
public class UserResponse {
	String id;
	String username;
	String firstname;
	String lastname;
	LocalDate birthday;
	Set<RoleResponse> roles;
}
