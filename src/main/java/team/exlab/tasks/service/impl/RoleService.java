package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.repository.RoleRepository;
import team.exlab.tasks.service.dto.RoleDtoResponse;
import team.exlab.tasks.service.interfaces.IRoleService;
import team.exlab.tasks.service.mapper.RoleMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDtoResponse> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::convertEntityToDto)
                .toList();
    }
}
