package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.WorkspaceDtoResponse;
import team.exlab.tasks.service.interfaces.IWorkspaceService;

import java.util.List;

import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.WORKSPACES;

@RestController
@RequestMapping(API + WORKSPACES)
@RequiredArgsConstructor
public class WorkspaceController {
    private final IWorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<List<WorkspaceDtoResponse>> getAll() {
        return ResponseEntity.ok(workspaceService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<WorkspaceDtoResponse> create(@Validated @RequestBody CreateWorkspaceDtoRequest workspaceDto) {
        return new ResponseEntity<>(
                workspaceService.create(workspaceDto),
                HttpStatus.CREATED
        );
    }

   /* @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CreateWorkspaceDtoRequest> update(@PathVariable Long id, @RequestBody CreateWorkspaceDtoRequest newWorkspace) {
        return workspaceService.update(id, newWorkspace)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<WorkspaceDtoResponse> getWorkspaceById(@PathVariable Long id) {
        return new ResponseEntity<>(
                workspaceService.getById(id),
                HttpStatus.OK
        );
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        return new ResponseEntity<>(
                workspaceService.deleteById(id),
                HttpStatus.OK
        );
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("{id}/users")
    public ResponseEntity<List<UserDtoResponse>> getAllUsersByWorkspaceId(@PathVariable Long id) {
        return new ResponseEntity<>(
                workspaceService.getAllUsersByWorkspaceId(id),
                HttpStatus.OK
        );
    }
}
