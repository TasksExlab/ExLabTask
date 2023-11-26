package team.exlab.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.CreateInviteDto;
import team.exlab.tasks.service.exception.ApiError;
import team.exlab.tasks.service.interfaces.IInviteService;
import team.exlab.tasks.service.validation.ValidationErrorResponse;

import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.INVITE_SEND;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
@Tag(name = "Контроллер приглашений",
        description = "Отправление приглашений")
public class InviteController {
    private final IInviteService inviteService;

    @PreAuthorize("hasAnyAuthority('PM', 'ADMIN')")
    @PostMapping(value = "workspaces/{workspaceId}" + INVITE_SEND)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Приглашение нового пользователя",
            description = "Позволяет отправить приглашение в рабочее пространство новому пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Приглашение успешно отправлено",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Невалидные данные",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}
            ),
            @ApiResponse(responseCode = "403",
                    description = "Недостаточно прав"
            ),
            @ApiResponse(responseCode = "404",
                    description = "Рабочее пространство не найдено",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Непредвиденная ошибка"
            )
    })
    public ResponseEntity<BaseResponse> sendInvite(
            @PathVariable @Parameter(description = "Идентификатор рабочего пространства") Long workspaceId,
            @Validated @RequestBody CreateInviteDto inviteDto
    ) {
        return new ResponseEntity<>(
                inviteService.sendInvite(workspaceId, inviteDto),
                HttpStatus.OK
        );
    }
}
