package team.exlab.tasks.service.invite_registration.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.dto.UserPresentDto;
import team.exlab.tasks.model.dto.WorkspaceDto;
import team.exlab.tasks.model.entity.WorkspaceEntity;
import team.exlab.tasks.model.mapper.UserConverter;
import team.exlab.tasks.model.mapper.WorkspaceConverter;
import team.exlab.tasks.model.repository.WorkspaceRepository;
import team.exlab.tasks.service.invite_registration.IWorkSpaceService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements IWorkSpaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceConverter workspaceConverter;
    private final UserConverter userConverter;

    @Override
    public WorkspaceEntity create(WorkspaceDto workspaceDto) {
        return workspaceRepository.save(workspaceConverter.convertWorkspaceFromDto(workspaceDto));
    }

    @Override
    public Optional<WorkspaceDto> update(Long id, WorkspaceDto update) {
        Optional<WorkspaceEntity> existedWorkspace = workspaceRepository.findById(id);
        if (existedWorkspace.isPresent()) {
            WorkspaceEntity workspace = existedWorkspace.get();
            workspaceConverter.convertWorkspaceFromDtoToEntity(update, workspace);
            return Optional.of(workspaceConverter.convertWorkspaceToDto(workspaceRepository.save(workspace)));
        }
        return Optional.empty();
    }

    @Override
    public List<UserPresentDto> getAllUsersByWorkspaceId(Long id) {
        return workspaceRepository.findById(id)
                .map(workspaceEntity -> workspaceEntity.getUsers()
                        .stream()
                        .map(userConverter::convertFromUserEntityToUserPresentDto)
                        .toList()).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkspaceDto> getById(Long id) {
        return workspaceRepository.findById(id)
                .map(workspaceConverter::convertWorkspaceToDto);
    }


    @Override
    @Transactional(readOnly = true)
    public List<WorkspaceDto> getAll() {
        return workspaceRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(workspaceConverter::convertWorkspaceToDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        workspaceRepository.findById(id)
                .ifPresent(workspaceRepository::delete);
    }
}
