package team.exlab.tasks.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import team.exlab.tasks.controller.integration.IntegrationTest;
import team.exlab.tasks.controller.integration.PostgreSQLExtension;
import team.exlab.tasks.model.enam.UserRole;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static team.exlab.tasks.util.UrlPathUtil.*;

@IntegrationTest
@AutoConfigureMockMvc
@ExtendWith(PostgreSQLExtension.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user.exlab@gmail.com")
    void changePassword() throws Exception {
        String body = """
                  {
                    "password": "Password10",
                    "passwordConfirm": "Password10"
                  }
                """;

        mockMvc.perform(patch(API + ACCOUNT + CHANGE_PASSWORD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());

        String authenticationBody = """
                  {
                    "email": "user.exlab@gmail.com",
                    "password": "Password10"
                  }
                """;

        mockMvc.perform(post(API + LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authenticationBody))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user.exlab@gmail.com")
    void changePasswordDifferentPasswords() throws Exception {
        String body = """
                  {
                    "password": "password",
                    "passwordConfirm": "password1"
                  }
                """;

        mockMvc.perform(patch(API + ACCOUNT + CHANGE_PASSWORD)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations").exists())
                .andExpect(jsonPath("$.violations", hasSize(1)));
    }
    @Test
    @WithMockUser(username = "user.exlab@gmail.com")
    void getUserInfo() throws Exception {
        mockMvc.perform(get(API + ACCOUNT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.email", equalTo("user.exlab@gmail.com")))
                .andExpect(jsonPath("$.role", equalTo("Project member")));
    }
}