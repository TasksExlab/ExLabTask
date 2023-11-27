package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.repository.WorkspaceRepository;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.UpdateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.WorkspaceDtoResponse;
import team.exlab.tasks.service.exception.NotFoundException;
import team.exlab.tasks.service.interfaces.IWorkspaceService;
import team.exlab.tasks.service.interfaces.IWorkspaceValidationService;
import team.exlab.tasks.service.mapper.UserConverter;
import team.exlab.tasks.service.mapper.WorkspaceConverter;

import java.util.List;
import java.util.Optional;

import static team.exlab.tasks.util.MessagesConstants.SUCCESSFUL_DELETE_WORKSPACE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkspaceService implements IWorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceConverter workspaceConverter;
    private final UserConverter userConverter;

    @Override
    @Transactional
    public WorkspaceDtoResponse create(CreateWorkspaceDtoRequest workspaceDto) {
        return Optional.of(workspaceDto)
                .map(workspaceConverter::convertDtoToWorkspace)
                .map(workspaceRepository::save)
                .map(workspaceConverter::convertWorkspaceToDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public WorkspaceDtoResponse update(Long id, UpdateWorkspaceDtoRequest request) {
        return workspaceRepository.findById(id)
                .map(workspaceEntity -> {
                    workspaceEntity.setName(request.getName());
                    workspaceEntity.setDescription(request.getDescription());
                    return workspaceEntity;
                })
                .map(workspaceConverter::convertWorkspaceToDto)
                .orElseThrow(() -> new NotFoundException(
                        "workspace.not.found",
                        String.format("Рабочее пространство (id = %s) не найдено", id)
                ));
    }

    @Override
    @Transactional
    public BaseResponse deleteById(Long id) {
        workspaceRepository.delete(
                workspaceRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                        "workspace.not.found",
                                        String.format("Рабочее пространство (id = %s) не найдено", id)
                                )
                        )
        );
        return new BaseResponse(SUCCESSFUL_DELETE_WORKSPACE);
    }

    @Override
    public List<UserDtoResponse> getAllUsersByWorkspaceId(Long id) {
        return workspaceRepository.findById(id)
                .map(workspaceEntity ->
                        workspaceEntity.getUsers()
                                .stream()
                                .map(userConverter::convertEntityToDto)
                                .toList())
                .orElseThrow(() -> new NotFoundException(
                        "workspace.not.found",
                        String.format("Рабочее пространство (id = %s) не найдено", id)
                ));
    }

    @Override
    public WorkspaceDtoResponse getById(Long id) {
        return workspaceRepository.findById(id)
                .map(workspaceConverter::convertWorkspaceToDto)
                .orElseThrow(() -> new NotFoundException(
                        "workspace.not.found",
                        String.format("Рабочее пространство (id = %s) не найдено", id)
                ));
    }


    @Override
    public List<WorkspaceDtoResponse> getAll() {
        return workspaceRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(workspaceConverter::convertWorkspaceToDto)
                .toList();
    }
}
