package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import team.exlab.tasks.controller.integration.IntegrationTest;
import team.exlab.tasks.controller.integration.PostgreSQLExtension;
import team.exlab.tasks.service.interfaces.IEmailService;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static team.exlab.tasks.util.UrlPathUtil.*;

@IntegrationTest
@AutoConfigureMockMvc
@ExtendWith(PostgreSQLExtension.class)
class InviteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Autowired
    private IEmailService emailService;


    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void sendInvite() throws Exception {
        Mockito.doNothing().when(emailService).sendMessageWithAttachment(any(String.class), any(String.class), any(String.class));

        String body = """
                  {
                    "roleName": "USER",
                    "email": "user3.exlab@gmail.com"
                  }
                """;

        mockMvc.perform(post(API + WORKSPACES + "/1" + INVITE_SEND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());

        verify(emailService, times(1)).sendMessageWithAttachment(any(String.class), any(String.class), any(String.class));
    }

    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void sendInviteWIthWrongData() throws Exception {
        String body = """
                  {
                    "roleName": "USE",
                    "email": "user.exlab@gmail.com"
                  }
                """;

        mockMvc.perform(post(API + WORKSPACES + "/1" + INVITE_SEND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations").exists())
                .andExpect(jsonPath("$.violations", hasSize(1)));
    }
}