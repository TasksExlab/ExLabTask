package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.UserPresentDto;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.model.entity.WorkspaceEntity;

import java.util.List;
import java.util.Optional;

public interface IWorkSpaceService {
    WorkspaceEntity create(CreateWorkspaceDtoRequest workspaceDto);

    Optional<CreateWorkspaceDtoRequest> update(Long id, CreateWorkspaceDtoRequest update);

    List<UserPresentDto> getAllUsersByWorkspaceId(Long id);

    Optional<CreateWorkspaceDtoRequest> getById(Long id);

    List<CreateWorkspaceDtoRequest> getAll();

    void deleteById(Long id);
}
