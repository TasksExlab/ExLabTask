package team.exlab.tasks.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import team.exlab.tasks.controller.integration.IntegrationTest;
import team.exlab.tasks.controller.integration.PostgreSQLExtension;

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
class WorkspaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void getAll() throws Exception {
        mockMvc.perform(get(API + WORKSPACES))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void getWorkspaceById() throws Exception {
        mockMvc.perform(get(API + WORKSPACES + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Workspace")));
    }
    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void getWorkspaceByWrongId() throws Exception {
        mockMvc.perform(get(API + WORKSPACES + "/afas"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", equalTo("id.format.invalid")));

        mockMvc.perform(get(API + WORKSPACES + "/10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.errorCode", equalTo("workspace.not.found")));
    }

    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void getAllUsersByWorkspaceId() throws Exception {
        mockMvc.perform(get(API + WORKSPACES + "/1" + USERS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void getAllUsersByWorkspaceIdWithNoUsers() throws Exception {

        mockMvc.perform(get(API + WORKSPACES + "/2" + USERS))
                .andExpect(status().isNoContent());
    }
    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void createWorkspace() throws Exception {
        String body = """
                  {
                    "name": "New workspace",
                    "description": ""
                  }
                """;

        mockMvc.perform(post(API + WORKSPACES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("New workspace")))
                .andExpect(jsonPath("$.id", equalTo(3)));

        mockMvc.perform(get(API + WORKSPACES + "/3"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void createDuplicateWorkspace() throws Exception {
        String body = """
                  {
                    "name": "Workspace",
                    "description": ""
                  }
                """;

        mockMvc.perform(post(API + WORKSPACES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations").exists())
                .andExpect(jsonPath("$.violations", hasSize(1)));
    }
    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void updateWorkspace() throws Exception {
        String body = """
                  {
                    "name": "Updated workspace",
                    "description": "Updated description"
                  }
                """;

        mockMvc.perform(put(API + WORKSPACES + "/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Updated workspace")))
                .andExpect(jsonPath("$.description", equalTo("Updated description")))
                .andExpect(jsonPath("$.id", equalTo(2)));

        mockMvc.perform(get(API + WORKSPACES + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Updated workspace")))
                .andExpect(jsonPath("$.description", equalTo("Updated description")));
    }
    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void updateWorkspaceToDuplicate() throws Exception {
        String body = """
                  {
                    "name": "Workspace",
                    "description": "Updated description"
                  }
                """;

        mockMvc.perform(put(API + WORKSPACES + "/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations").exists())
                .andExpect(jsonPath("$.violations", hasSize(1)));

    }

    @Test
    @WithMockUser(username = "admin.exlab@gmail.com", authorities = {"ADMIN"})
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(API + WORKSPACES + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());

        mockMvc.perform(get(API + WORKSPACES))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}