package team.exlab.tasks.service.dto.workspace;

import lombok.Data;

@Data
public class CreateWorkspaceDtoRequest {
    private String workspaceName;
    private String descriptionWorkspace;
}
