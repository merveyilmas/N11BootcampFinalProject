package com.merveyilmaz.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.merveyilmaz.userservice.UserServiceApplication;
import com.merveyilmaz.userservice.enums.EnumGender;
import com.merveyilmaz.userservice.enums.EnumRate;
import com.merveyilmaz.userservice.enums.EnumStatus;
import com.merveyilmaz.userservice.request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {UserServiceApplication.class})
class UserControllerTest extends BaseControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    @Mock
    private EnumGender gender;
    @Mock
    private EnumStatus status;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldGetRecommendRestaurants() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/recommend-restaurant/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldSaveUser() throws Exception {
        Long userId = 1L;
        String firstName = "merve";
        String lastName = "yılmaz";
        String password = "12345";
        LocalDateTime userCreateDate = LocalDateTime.of(1990, Month.MAY, 15, 10, 30);
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String email = "yilmaz.67@outlook.com";
        Double latitude = 123.123;
        Double longitude = 123.123;
        gender = gender.FEMALE;
        status = status.ACTIVE;

        UserSaveRequest request = new UserSaveRequest(
                firstName, lastName, password, userCreateDate, birthDate,
                email, latitude, longitude, gender, status
        );

        String requestAsString = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldDeleteUser() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/103")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);

        assertTrue(success);
    }

    @Test
    void shouldUpdateUser() throws Exception {
        Long userId = 1L;
        String firstName = "merve";
        String lastName = "yılmaz";
        String password = "12345";
        LocalDateTime userCreateDate = LocalDateTime.of(1990, Month.MAY, 15, 10, 30);
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String email = "yilmaz.67@outlook.com";
        Double latitude = 123.123;
        Double longitude = 123.123;
        gender = gender.FEMALE;
        status = status.ACTIVE;

        UserUpdateRequest request = new UserUpdateRequest(
                userId, firstName, lastName, password, userCreateDate,
                birthDate, email, latitude, longitude, gender, status
        );

        String requestAsString = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/202")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);

        assertTrue(success);
    }

    @Test
    void shouldUpdateUserPassword() throws Exception {
        UserUpdatePasswordRequest request = new UserUpdatePasswordRequest(
                "oldPass", "newPass");

        String requestAsString = objectMapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/users/202/password")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);

        assertTrue(success);
    }
}