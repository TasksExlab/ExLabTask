package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.model.entity.WorkspaceEntity;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.UserPresentDto;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.UpdateWorkspaceDtoResponse;
import team.exlab.tasks.service.dto.workspace.WorkspaceDtoResponse;

import java.util.List;
import java.util.Optional;

public interface IWorkspaceService {
    WorkspaceDtoResponse create(CreateWorkspaceDtoRequest workspaceDto);

    WorkspaceDtoResponse update(Long id, UpdateWorkspaceDtoResponse update);

    List<UserDtoResponse> getAllUsersByWorkspaceId(Long id);

    WorkspaceDtoResponse getById(Long id);

    List<WorkspaceDtoResponse> getAll();

    BaseResponse deleteById(Long id);
}
