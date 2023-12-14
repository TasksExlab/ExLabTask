package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.UpdateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.WorkspaceDtoResponse;

import java.util.List;

public interface IWorkspaceService {
    WorkspaceDtoResponse create(CreateWorkspaceDtoRequest workspaceDto);

    WorkspaceDtoResponse update(Long id, UpdateWorkspaceDtoRequest update);

    List<UserDtoResponse> getAllUsersByWorkspaceId(Long id);

    WorkspaceDtoResponse getById(Long id);

    List<WorkspaceDtoResponse> getAll();

    BaseResponse deleteById(Long id);
}
