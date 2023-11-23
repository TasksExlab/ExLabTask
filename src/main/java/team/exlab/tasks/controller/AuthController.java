package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.dto.user.LoginUserDtoRequest;
import team.exlab.tasks.service.impl.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createAuthToken(@Validated @RequestBody LoginUserDtoRequest logUserDto) {
        return new ResponseEntity<>(
                authService.createAuthToken(logUserDto),
                HttpStatus.OK
        );
    }
}
