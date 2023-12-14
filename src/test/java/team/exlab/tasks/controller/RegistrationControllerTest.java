package team.exlab.tasks.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import team.exlab.tasks.controller.integration.IntegrationTest;
import team.exlab.tasks.controller.integration.PostgreSQLExtension;
import team.exlab.tasks.model.repository.InviteRepository;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static team.exlab.tasks.util.UrlPathUtil.*;

@IntegrationTest
@AutoConfigureMockMvc
@ExtendWith(PostgreSQLExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRegistrationCredentials() throws Exception {
        mockMvc.perform(get(API + REGISTRATION + "/1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void getRegistrationCredentialsExpiredInvite() throws Exception {
        mockMvc.perform(get(API + REGISTRATION + "/qwertyuiop123456789"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.errorCode", equalTo("link.has.expired")));
    }
    @Test
    void getRegistrationCredentialsWrongInvite() throws Exception {
        mockMvc.perform(get(API + REGISTRATION + "/qwert6789"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.errorCode", equalTo("invite.not.found")));
    }

    @Test
    void createNewUser() throws Exception {
        String body = """
                  {
                    "name": "Rser",
                    "surname": "Rseruser",
                    "email": "user3.exlab@gmail.com",
                    "password": "password10",
                    "passwordConfirm": "password10",
                    "isUserAgreeToProcessPersonalData": "true"
                  }
                """;

        mockMvc.perform(post(API + REGISTRATION + "/1234")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").exists());
    }
}