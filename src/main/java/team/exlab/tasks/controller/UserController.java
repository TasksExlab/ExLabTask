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
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.interfaces.IUserService;
import team.exlab.tasks.service.validation.ValidationErrorResponse;

import java.security.Principal;

import static team.exlab.tasks.util.UrlPathUtil.ACCOUNT;
import static team.exlab.tasks.util.UrlPathUtil.API;

@RestController
@RequestMapping(API + ACCOUNT)
@RequiredArgsConstructor
@Tag(name = "Контроллер данных пользователя",
        description = "Позволяет выполнять действия с аккаунтом пользователя")
public class UserController {
    private final IUserService userService;

    @PatchMapping("/password")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Изменение пароля пользователя",
            description = "Позволяет изменить пароль пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешное изменение пароля",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Невалидные данные",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Непредвиденная ошибка"
            )
    })
    public ResponseEntity<BaseResponse> changePassword(
            @Validated @RequestBody ChangePasswordUserDtoRequest request,
            Principal principal
    ) {
        return new ResponseEntity<>(
                userService.changePassword(principal.getName(), request),
                HttpStatus.OK
        );
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Получение данных об аккаунте пользователе",
            description = "Позволяет получить данные об аккаунте пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Данные о пользоваете успешно получены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDtoResponse.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Непредвиденная ошибка"
            )
    })
    public ResponseEntity<UserDtoResponse> getUserInfo(Principal principal) {
        return new ResponseEntity<>(
                userService.getUserByUsername(principal.getName()),
                HttpStatus.OK
        );
    }
}
