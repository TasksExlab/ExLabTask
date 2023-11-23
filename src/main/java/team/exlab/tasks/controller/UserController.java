package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.interfaces.IUserService;

import java.security.Principal;

import static team.exlab.tasks.util.PathUrlUtil.API;
import static team.exlab.tasks.util.PathUrlUtil.PROFILE;

@RestController
@RequestMapping(API + PROFILE)
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@Validated @RequestBody ChangePasswordUserDtoRequest request,
                                            Principal principal) {
        userService.changePassword(principal.getName(), request);
        return ResponseEntity.ok()
                .build();
    }


}
