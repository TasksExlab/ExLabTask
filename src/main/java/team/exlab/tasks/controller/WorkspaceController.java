package team.exlab.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.dto.workspace.CreateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.UpdateWorkspaceDtoRequest;
import team.exlab.tasks.service.dto.workspace.WorkspaceDtoResponse;
import team.exlab.tasks.service.exception.ApiError;
import team.exlab.tasks.service.interfaces.IWorkspaceService;
import team.exlab.tasks.service.validation.ValidationErrorResponse;
import team.exlab.tasks.service.validation.group.ValidationSequence;

import java.util.List;

import static team.exlab.tasks.util.UrlPathUtil.*;

@RestController
@RequestMapping(API + WORKSPACES)
@RequiredArgsConstructor
@Tag(name = "Workspace controller",
        description = "Operations with workspaces")
public class WorkspaceController {
    private final IWorkspaceService workspaceService;

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get all workspaces",
            description = "Returns all workspaces array")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Workspaces successfully returned",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = WorkspaceDtoResponse.class)))}
            ),
            @ApiResponse(responseCode = "204",
                    description = "No workspaces found"
            ),
            @ApiResponse(responseCode = "403",
                    description = "Not enough authorities"
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    public ResponseEntity<List<WorkspaceDtoResponse>> getAllWorkspaces() {
        var workspaces = workspaceService.getAll();
        if (workspaces.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                workspaceService.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create new workspace",
            description = "Creates and returns new workspace")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Workspace successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkspaceDtoResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}
            ),
            @ApiResponse(responseCode = "403",
                    description = "Not enough authorities"
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    public ResponseEntity<WorkspaceDtoResponse> createWorkspace(
            @Validated(ValidationSequence.class) @RequestBody CreateWorkspaceDtoRequest workspaceDto
    ) {
        return new ResponseEntity<>(
                workspaceService.create(workspaceDto),
                HttpStatus.CREATED
        );
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update workspace by id",
            description = "Updates and returns workspace by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Workspace successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkspaceDtoResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Workspace not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "403",
                    description = "Not enough authorities"
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<WorkspaceDtoResponse> updateWorkspace(
            @PathVariable Long id,
            @Validated(ValidationSequence.class) @RequestBody UpdateWorkspaceDtoRequest request
    ) {
        return new ResponseEntity<>(
                workspaceService.update(id, request),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get workspace by id",
            description = "Returns workspace by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Workspace successfully returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WorkspaceDtoResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "403",
                    description = "Not enough authorities"
            ),
            @ApiResponse(responseCode = "404",
                    description = "Workspace not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    public ResponseEntity<WorkspaceDtoResponse> getWorkspaceById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                workspaceService.getById(id),
                HttpStatus.OK
        );
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete workspace by id",
            description = "Delete workspace by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Workspace successfully deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "403",
                    description = "Not enough authorities"
            ),
            @ApiResponse(responseCode = "404",
                    description = "Workspace not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteWorkspaceById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                workspaceService.deleteById(id),
                HttpStatus.OK
        );
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get users by workspace id",
            description = "Returns user array by workspace id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User array successfully returned",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDtoResponse.class)))}
            ),
            @ApiResponse(responseCode = "204",
                    description = "No users found"
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "403",
                    description = "Not enough authorities"
            ),
            @ApiResponse(responseCode = "404",
                    description = "Workspace not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    @GetMapping("/{id}" + USERS)
    public ResponseEntity<List<UserDtoResponse>> getAllUsersByWorkspaceId(
            @PathVariable Long id
    ) {
        var users = workspaceService.getAllUsersByWorkspaceId(id);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                workspaceService.getAllUsersByWorkspaceId(id),
                HttpStatus.OK
        );
    }
}
