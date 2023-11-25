package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.model.entity.WorkspaceEntity;
import team.exlab.tasks.service.dto.UserPresentDto;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.impl.WorkspaceService;

import java.util.List;

import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.WORKSPACES;

@RestController
@RequestMapping(API + WORKSPACES)
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<List<CreateWorkspaceDtoRequest>> getAll() {
        return ResponseEntity.ok(workspaceService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<WorkspaceEntity> create(@RequestBody CreateWorkspaceDtoRequest workspaceDto) {
        return ResponseEntity.ok(workspaceService.create(workspaceDto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CreateWorkspaceDtoRequest> update(@PathVariable Long id, @RequestBody CreateWorkspaceDtoRequest newWorkspace) {
        return workspaceService.update(id, newWorkspace)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<CreateWorkspaceDtoRequest> getWorkspaceById(@PathVariable Long id) {
        return workspaceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return ResponseEntity.ok(workspaceService.getById(id));
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("{id}/users")
    public ResponseEntity<List<UserPresentDto>> getAllUsersByWorkspaceId(@PathVariable Long id) {
        return ResponseEntity.ok(workspaceService.getAllUsersByWorkspaceId(id));
    }
}
