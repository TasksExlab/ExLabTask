package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.exlab.tasks.service.dto.UserPresentDto;
import team.exlab.tasks.service.dto.WorkspaceDto;
import team.exlab.tasks.model.entity.WorkspaceEntity;
import team.exlab.tasks.service.impl.WorkspaceService;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;


    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> getAll() {
        return ResponseEntity.ok(workspaceService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<WorkspaceEntity> create(@RequestBody WorkspaceDto workspaceDto) {
        return ResponseEntity.ok(workspaceService.create(workspaceDto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<WorkspaceDto> update(@PathVariable Long id, @RequestBody WorkspaceDto newWorkspace) {
        return workspaceService.update(id, newWorkspace)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<WorkspaceDto> getWorkspaceById(@PathVariable Long id) {
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
