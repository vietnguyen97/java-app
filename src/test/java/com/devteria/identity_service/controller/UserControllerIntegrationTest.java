package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.UserCreatedRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@Testcontainers
public class UserControllerIntegrationTest {
    @Container
    static final PostgreSQLContainer<?> POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:17-alpine");

    @DynamicPropertySource
    static void configurePostgreSQL(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_SQL_CONTAINER::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }
    @Autowired
    private MockMvc mockMvc;

    private UserCreatedRequest request;
    private UserResponse response;
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
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        String content = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("data.username").value("nguyen"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.firstname").value("Viet"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.lastname").value("Nguyen"));
    }
}
