package team.exlab.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.dto.user.LoginUserDtoRequest;
import team.exlab.tasks.service.impl.AuthService;
import team.exlab.tasks.service.validation.ValidationErrorResponse;

import static team.exlab.tasks.util.UrlPathUtil.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API)
@Tag(name = "Authentication controller",
        description = "User authentication")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "User authentication",
            description = "Authenticate user and return JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful authentication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class))}
            ),
            @ApiResponse(responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    public ResponseEntity<JwtResponse> createAuthToken(
            @Validated @RequestBody LoginUserDtoRequest logUserDto
    ) {
        return new ResponseEntity<>(
                authService.createAuthToken(logUserDto),
                HttpStatus.OK
        );
    }
}
