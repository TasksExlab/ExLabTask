package team.exlab.tasks.service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.entity.WorkspaceEntity;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;

@RequiredArgsConstructor
@Component
public class WorkspaceConverter {

    private final ModelMapper modelMapper;

    public WorkspaceEntity convertWorkspaceFromDto(CreateWorkspaceDtoRequest workspaceDto) {
        return modelMapper.map(workspaceDto, WorkspaceEntity.class);
    }

    public CreateWorkspaceDtoRequest convertWorkspaceToDto(WorkspaceEntity workspace) {
        return modelMapper.map(workspace, CreateWorkspaceDtoRequest.class);
    }

    public void convertWorkspaceFromDtoToEntity(CreateWorkspaceDtoRequest workspaceDto, WorkspaceEntity workspace) {
        modelMapper.map(workspaceDto, workspace);
    }
}
