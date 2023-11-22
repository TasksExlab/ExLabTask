package team.exlab.tasks.service.invite_registration;

import team.exlab.tasks.model.dto.UserPresentDto;
import team.exlab.tasks.model.dto.WorkspaceDto;
import team.exlab.tasks.model.entity.WorkspaceEntity;

import java.util.List;
import java.util.Optional;

public interface IWorkSpaceService {
    WorkspaceEntity create(WorkspaceDto workspaceDto);

    Optional<WorkspaceDto> update(Long id, WorkspaceDto update);

    List<UserPresentDto> getAllUsersByWorkspaceId(Long id);

    Optional<WorkspaceDto> getById(Long id);

    List<WorkspaceDto> getAll();

    void deleteById(Long id);
}
