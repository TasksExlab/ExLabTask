package team.exlab.tasks.service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.service.dto.WorkspaceDto;
import team.exlab.tasks.model.entity.WorkspaceEntity;

@RequiredArgsConstructor
@Component
public class WorkspaceConverter {

    private final ModelMapper modelMapper;

    public WorkspaceEntity convertWorkspaceFromDto(WorkspaceDto workspaceDto) {
        return modelMapper.map(workspaceDto, WorkspaceEntity.class);
    }

    public WorkspaceDto convertWorkspaceToDto(WorkspaceEntity workspace) {
        return modelMapper.map(workspace, WorkspaceDto.class);
    }

    public void convertWorkspaceFromDtoToEntity(WorkspaceDto workspaceDto, WorkspaceEntity workspace) {
        modelMapper.map(workspaceDto, workspace);
    }
}
