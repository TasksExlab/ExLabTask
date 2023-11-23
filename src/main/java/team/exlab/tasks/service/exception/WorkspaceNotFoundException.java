package team.exlab.tasks.service.exception;

import org.springframework.http.HttpStatus;

public class WorkspaceNotFoundException extends BaseException {
    public WorkspaceNotFoundException(Long workspaceId) {
        super(ApiError.builder()
                        .errorCode("workspace.not.found")
                        .description("Workspace not found with id=" + workspaceId.toString())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
