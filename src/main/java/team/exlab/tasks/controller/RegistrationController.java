package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.dto.user.UserDto;
import team.exlab.tasks.service.impl.RegistrationService;
import team.exlab.tasks.service.interfaces.IRegistrationService;

import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.REGISTRATION;

@RestController
@RequiredArgsConstructor
@RequestMapping(API)
public class RegistrationController {
    private final IRegistrationService registrationService;

    @GetMapping(REGISTRATION + "/{uniqueIdentifier}")
    public ResponseEntity<BaseResponse> getRegistrationCredentials(@PathVariable String uniqueIdentifier) {
        return new ResponseEntity<>(
                registrationService.getRegistrationCredentials(uniqueIdentifier),
                HttpStatus.OK
        );
    }

    @PostMapping(REGISTRATION + "/{uniqueIdentifier}")
    public ResponseEntity<JwtResponse> createNewUser(@PathVariable String uniqueIdentifier,
                                                    @Validated @RequestBody CreateUserDtoRequest request) {
        return new ResponseEntity<>(
                registrationService.createNewUser(uniqueIdentifier, request),
                HttpStatus.CREATED
        );
    }
}
