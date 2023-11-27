package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.repository.WorkspaceRepository;
import team.exlab.tasks.service.interfaces.IWorkspaceValidationService;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkspaceValidationService implements IWorkspaceValidationService {
    private final WorkspaceRepository workspaceRepository;

    @Override
    public boolean isValidName(String name) {
        return !workspaceRepository.existsByName(name);
    }
}
