package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.UpdateWorkspaceDtoRequest;

public interface IWorkspaceValidationService {
//    void validate(CreateWorkspaceDtoRequest workspaceDto);
//    void validate(UpdateWorkspaceDtoRequest workspaceDto);
    boolean isValidName(String name);
}
