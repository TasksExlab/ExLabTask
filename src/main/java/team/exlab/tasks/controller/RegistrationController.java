package team.exlab.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.exception.ApiError;
import team.exlab.tasks.service.interfaces.IRegistrationService;
import team.exlab.tasks.service.validation.ValidationErrorResponse;

import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.REGISTRATION;

@RestController
@RequiredArgsConstructor
@RequestMapping(API)
@Tag(name = "Контроллер регистрации",
        description = "Регистрация нового пользователя")
public class RegistrationController {
    private final IRegistrationService registrationService;

    @GetMapping(REGISTRATION + "/{uniqueIdentifier}")
    @Operation(summary = "Страница регистрации нового пользователя",
            description = "Пользователь перешел по ссылке регистрации")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешный переход на страницу регистрации",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Ссылка уже была активирована или срок действия ссылки истек",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Приглашение не найдено",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Непредвиденная ошибка"
            )
    })
    public ResponseEntity<BaseResponse> getRegistrationCredentials(
            @PathVariable @Parameter(description = "Уникальный идентификатор регистрации") String uniqueIdentifier
    ) {
        return new ResponseEntity<>(
                registrationService.getRegistrationCredentials(uniqueIdentifier),
                HttpStatus.OK
        );
    }
    @PostMapping(REGISTRATION + "/{uniqueIdentifier}")
    @Operation(summary = "Регистрация нового пользователя",
            description = "Позволяет зарегистрировать нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Успешная регистрации",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Невалидные данные",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Ссылка уже была активирована или срок действия ссылки истек",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Приглашение не найдено",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Непредвиденная ошибка"
            )
    })
    public ResponseEntity<JwtResponse> createNewUser(@PathVariable String uniqueIdentifier,
                                                     @Validated @RequestBody CreateUserDtoRequest request) {
        return new ResponseEntity<>(
                registrationService.createNewUser(uniqueIdentifier, request),
                HttpStatus.CREATED
        );
    }
}
