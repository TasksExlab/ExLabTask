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
import team.exlab.tasks.service.validation.group.ValidationSequence;

import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.REGISTRATION;

@RestController
@RequiredArgsConstructor
@RequestMapping(API)
@Tag(name = "Registration controller",
        description = "Register new user")
public class RegistrationController {
    private final IRegistrationService registrationService;

    @GetMapping(REGISTRATION + "/{uniqueIdentifier}")
    @Operation(summary = "Registration new user page",
            description = "When user open invite link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful redirect to registration page",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BaseResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Link has been activated or the link has expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Invite not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    public ResponseEntity<BaseResponse> getRegistrationCredentials(
            @PathVariable @Parameter(description = "Invite unique identifier") String uniqueIdentifier
    ) {
        return new ResponseEntity<>(
                registrationService.getRegistrationCredentials(uniqueIdentifier),
                HttpStatus.OK
        );
    }

    @PostMapping(REGISTRATION + "/{uniqueIdentifier}")
    @Operation(summary = "Register new user",
            description = "Register new user, return JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successful registration",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Link has been activated or the link has expired",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Invite not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    public ResponseEntity<JwtResponse> createNewUser(
            @PathVariable String uniqueIdentifier,
            @Validated(ValidationSequence.class) @RequestBody CreateUserDtoRequest request
    ) {
        return new ResponseEntity<>(
                registrationService.createNewUser(uniqueIdentifier, request),
                HttpStatus.CREATED
        );
    }
}
