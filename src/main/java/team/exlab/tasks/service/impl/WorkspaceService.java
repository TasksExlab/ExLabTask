package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.repository.WorkspaceRepository;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.UpdateWorkspaceDtoResponse;
import team.exlab.tasks.service.dto.workspace.WorkspaceDtoResponse;
import team.exlab.tasks.service.exception.NotFoundException;
import team.exlab.tasks.service.interfaces.IWorkspaceService;
import team.exlab.tasks.service.interfaces.IWorkspaceValidationService;
import team.exlab.tasks.service.mapper.UserConverter;
import team.exlab.tasks.service.mapper.WorkspaceConverter;
import team.exlab.tasks.util.MessagesConstants;

import java.util.List;
import java.util.Optional;

import static team.exlab.tasks.util.MessagesConstants.*;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkspaceService implements IWorkspaceService, IWorkspaceValidationService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceConverter workspaceConverter;
    private final UserConverter userConverter;

    @Override
    public WorkspaceDtoResponse create(CreateWorkspaceDtoRequest workspaceDto) {
        return Optional.of(workspaceDto)
                .map(workspaceConverter::convertDtoToWorkspace)
                .map(workspaceRepository::save)
                .map(workspaceConverter::convertWorkspaceToDto)
                .orElseThrow();
    }

    @Override
    public WorkspaceDtoResponse update(Long id, UpdateWorkspaceDtoResponse update) {
        /*Optional<WorkspaceEntity> existedWorkspace = workspaceRepository.findById(id);
        if (existedWorkspace.isPresent()) {
            WorkspaceEntity workspace = existedWorkspace.get();
            workspaceConverter.convertWorkspaceFromDtoToEntity(update, workspace);
            return Optional.of(workspaceConverter.convertWorkspaceToDto(workspaceRepository.save(workspace)));
        }*/
        return null;
    }

    @Override
    public List<UserDtoResponse> getAllUsersByWorkspaceId(Long id) {
        return workspaceRepository.findById(id)
                .map(workspaceEntity ->
                        workspaceEntity.getUsers()
                                .stream()
                                .map(userConverter::convertEntityToDto)
                                .toList())
                .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public WorkspaceDtoResponse getById(Long id) {
        return workspaceRepository.findById(id)
                .map(workspaceConverter::convertWorkspaceToDto)
                .orElseThrow(() -> new NotFoundException(
                        "workspace.not.found",
                        String.format("Рабочее пространство (id = %s) не найдено", id)
                ));
    }


    @Override
    @Transactional(readOnly = true)
    public List<WorkspaceDtoResponse> getAll() {
        return workspaceRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(workspaceConverter::convertWorkspaceToDto)
                .toList();
    }

    @Override
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
    public boolean isValidName(String name) {
        return !workspaceRepository.existsByName(name);
    }
}
