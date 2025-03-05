package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.UserCreatedRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.assertj.core.api.Assertions;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreatedRequest request;
    private UserResponse response;
    private User user;
    private LocalDate date;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void initData() {
        date = LocalDate.of(1997, 1, 18);
        request = UserCreatedRequest
                .builder()
                .username("nguyen")
                .firstname("Viet")
                .lastname("Nguyen")
                .password("123456789")
                .birthday(date)
                .build();
        response = UserResponse
                .builder()
                .id("cf0600f538b3")
                .username("nguyen")
                .firstname("Viet")
                .lastname("Nguyen")
                .birthday(date)
                .build();
        user = User
                .builder()
                .id("cf0600f538b3")
                .username("nguyen")
                .firstname("Viet")
                .lastname("Nguyen")
                .birthday(date)
                .build();
    }

    @Test
    void createdUser_validRequest_success() throws Exception {
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        var resp = userService.createdUser(request);
        Assertions.assertThat(resp.getId()).isEqualTo("cf0600f538b3");
        Assertions.assertThat(resp.getUsername()).isEqualTo("nguyen");
    }

    @Test
    void createdUser_userExits_fail() throws Exception {
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(true);
        var exception = assertThrows(RuntimeException.class, () -> userService.createdUser(request));
        Assertions.assertThat(exception.getMessage()).isEqualTo("User existed");
    }
}
