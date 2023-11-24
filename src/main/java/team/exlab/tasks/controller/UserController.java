package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.interfaces.IUserService;

import java.security.Principal;

import static team.exlab.tasks.util.MessagesConstants.SUCCESSFUL_CHANGE_PASSWORD;
import static team.exlab.tasks.util.UrlPathUtil.API;
import static team.exlab.tasks.util.UrlPathUtil.PROFILE;

@RestController
@RequestMapping(API + PROFILE)
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@Validated @RequestBody ChangePasswordUserDtoRequest request,
                                            Principal principal) {
        userService.changePassword(principal.getName(), request);

        return new ResponseEntity<>(
                SUCCESSFUL_CHANGE_PASSWORD,
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<UserDtoResponse> getUserInfo(Principal principal) {
        return new ResponseEntity<>(
                userService.getUserByUsername(principal.getName()),
                HttpStatus.OK
        );
    }
}
