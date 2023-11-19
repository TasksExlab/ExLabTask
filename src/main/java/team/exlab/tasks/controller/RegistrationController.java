package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.service.invite_registration.impl.RegistrationServiceImpl;

import static team.exlab.tasks.util.PathUrlUtil.REGISTRATION;

@RestController
@RequiredArgsConstructor
@RequestMapping(REGISTRATION)
public class RegistrationController {

    private final RegistrationServiceImpl registrationService;


    @GetMapping("/workspace-invited/{workspaceId}/registration-new-user/{uniqueIdentifier}")
    public ResponseEntity<?> getRegistrationCredentials(@PathVariable String workspaceId,
                                                        @PathVariable String uniqueIdentifier) {
        return registrationService.getRegistrationCredentials(workspaceId, uniqueIdentifier);
    }


    @PostMapping("/workspace-invited/{workspaceId}/registration-new-user/{uniqueIdentifier}")
    public ResponseEntity<?> createNewUser(@PathVariable String workspaceId,
                                           @PathVariable String uniqueIdentifier,
                                           @RequestBody UserDto userDto) {
        return registrationService.createNewUser(workspaceId, uniqueIdentifier, userDto);
    }
}
