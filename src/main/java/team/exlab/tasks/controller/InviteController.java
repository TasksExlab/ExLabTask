package team.exlab.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import team.exlab.tasks.service.dto.CreateInviteDtoRequest;
import team.exlab.tasks.service.exception.ApiError;
import team.exlab.tasks.service.interfaces.IInviteService;
import team.exlab.tasks.service.validation.ValidationErrorResponse;

import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.INVITE_SEND;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
@Tag(name = "Invite controller",
        description = "Sending invites")
public class InviteController {
    private final IInviteService inviteService;

    @PostMapping(value = "workspaces/{id}" + INVITE_SEND)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Invite new user",
            description = "Send invite on email to register in app")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Invitation sent successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}
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
    public ResponseEntity<BaseResponse> sendInvite(
            @PathVariable Long id,
            @Validated @RequestBody CreateInviteDtoRequest inviteDto
    ) {
        return new ResponseEntity<>(
                inviteService.sendInvite(id, inviteDto),
                HttpStatus.OK
        );
    }
}
