package team.exlab.tasks.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import team.exlab.tasks.controller.integration.IntegrationTest;
import team.exlab.tasks.controller.integration.PostgreSQLExtension;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.ROLES;

@IntegrationTest
@AutoConfigureMockMvc
@ExtendWith(PostgreSQLExtension.class)
class RoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser()
    void getAllRoles() throws Exception {
        mockMvc.perform(get(API + ROLES))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}