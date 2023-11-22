package team.exlab.tasks.service.invite_registration;

import org.springframework.http.ResponseEntity;
import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.exception.InviteException;
import team.exlab.tasks.exception.RegistrationException;

public interface IRegistrationService {
    ResponseEntity<?> createNewUser(String workspaceId, String uniqueIdentifier,
                                    UserDto newUserDto) throws RegistrationException, InviteException;

    ResponseEntity<?> getRegistrationCredentials(String workspaceId, String uniqueIdentifier) throws RegistrationException, InviteException;
}
