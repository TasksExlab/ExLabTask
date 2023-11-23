package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.service.dto.user.UserDto;
import team.exlab.tasks.service.interfaces.IRegistrationService;

import static team.exlab.tasks.util.PathUrlUtil.REGISTRATION;

@RestController
@RequiredArgsConstructor
@RequestMapping(REGISTRATION)
public class RegistrationController {
    private final IRegistrationService registrationService;

    @GetMapping("/workspace-invited/{workspaceId}/registration-new-user/{uniqueIdentifier}")
    public ResponseEntity<?> getRegistrationCredentials(@PathVariable String workspaceId,
                                                        @PathVariable String uniqueIdentifier) {
        return registrationService.getRegistrationCredentials(workspaceId, uniqueIdentifier);
    }

    @PostMapping("/workspace-invited/{workspaceId}/registration-new-user/{uniqueIdentifier}")
    public ResponseEntity<?> createNewUser(@PathVariable String workspaceId,
                                           @PathVariable String uniqueIdentifier,
                                           @Validated @RequestBody UserDto userDto) {
        return registrationService.createNewUser(workspaceId, uniqueIdentifier, userDto);
    }
}
