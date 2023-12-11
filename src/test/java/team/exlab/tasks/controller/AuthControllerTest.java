package team.exlab.tasks.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import team.exlab.tasks.controller.integration.IntegrationTest;
import team.exlab.tasks.controller.integration.PostgreSQLExtension;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.LOGIN;

@IntegrationTest
@AutoConfigureMockMvc
@ExtendWith(PostgreSQLExtension.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAuthToken() throws Exception {
        String body = """
                  {
                    "email": "admin.exlab@gmail.com",
                    "password": "password"
                  }
                """;

        mockMvc.perform(post(API + LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void createAuthTokenInvalidPassword() throws Exception {
        String bodyWrongPassword = """
                  {
                    "email": "admin.exlab@gmail.com",
                    "password": "password1"
                  }
                """;

        mockMvc.perform(post(API + LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyWrongPassword))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.token").doesNotExist())
                .andExpect(jsonPath("$.violations", hasSize(1)));
    }

    @Test
    void createAuthTokenInvalidEmail() throws Exception {
        String bodyWrongPassword = """
                  {
                    "email": "dmin.exlab@gmail.com",
                    "password": "password"
                  }
                """;

        mockMvc.perform(post(API + LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyWrongPassword))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.token").doesNotExist())
                .andExpect(jsonPath("$.violations", hasSize(2)));
    }
}