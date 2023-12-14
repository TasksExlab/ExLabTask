package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.RoleDtoResponse;

import java.util.List;

public interface IRoleService {
    List<RoleDtoResponse> getAllRoles();
}
