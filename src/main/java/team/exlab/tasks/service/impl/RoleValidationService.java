package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.exlab.tasks.model.enam.UserRole;
import team.exlab.tasks.service.interfaces.IRoleValidationService;

@Service
@RequiredArgsConstructor
public class RoleValidationService implements IRoleValidationService {
    @Override
    public boolean isRoleValid(String name) {
        return UserRole.find(name).isPresent();
    }
}
