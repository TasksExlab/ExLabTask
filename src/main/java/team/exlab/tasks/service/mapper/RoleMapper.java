package team.exlab.tasks.service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.entity.Role;
import team.exlab.tasks.service.dto.RoleDtoResponse;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final ModelMapper modelMapper;

    public RoleDtoResponse convertEntityToDto(Role role) {
        var roleDto = modelMapper.map(role, RoleDtoResponse.class);
        roleDto.setName(role.getRole().name());
        return roleDto;
    }


}
