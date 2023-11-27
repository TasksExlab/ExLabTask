package team.exlab.tasks.service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.entity.WorkspaceEntity;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.UpdateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.WorkspaceDtoResponse;

@RequiredArgsConstructor
@Component
public class WorkspaceConverter {

    private final ModelMapper modelMapper;

    public WorkspaceEntity convertDtoToWorkspace(CreateWorkspaceDtoRequest workspaceDto) {
        return modelMapper.map(workspaceDto, WorkspaceEntity.class);
    }

    public WorkspaceDtoResponse convertWorkspaceToDto(WorkspaceEntity workspace) {
        return modelMapper.map(workspace, WorkspaceDtoResponse.class);
    }
}
