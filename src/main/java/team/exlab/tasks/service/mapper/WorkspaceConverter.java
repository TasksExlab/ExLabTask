package team.exlab.tasks.service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.entity.Workspace;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.WorkspaceDtoResponse;

@RequiredArgsConstructor
@Component
public class WorkspaceConverter {

    private final ModelMapper modelMapper;

    public Workspace convertDtoToWorkspace(CreateWorkspaceDtoRequest workspaceDto) {
        return modelMapper.map(workspaceDto, Workspace.class);
    }

    public WorkspaceDtoResponse convertWorkspaceToDto(Workspace workspace) {
        return modelMapper.map(workspace, WorkspaceDtoResponse.class);
    }
}
