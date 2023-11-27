package team.exlab.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.exlab.tasks.service.dto.RoleDtoResponse;
import team.exlab.tasks.service.interfaces.IRoleService;

import java.util.List;

import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.ROLES;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + ROLES)
@Tag(name = "Authority controller",
        description = "Operations with authorities")
public class RoleController {
    private final IRoleService roleService;

    @GetMapping
    @Operation(summary = "Get all authorities",
            description = "Returns authorities array")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Authorities successful returned",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RoleDtoResponse.class)))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    public ResponseEntity<List<RoleDtoResponse>> getAllRoles() {
        return new ResponseEntity<>(
                roleService.getAllRoles(),
                HttpStatus.OK
        );
    }
}
