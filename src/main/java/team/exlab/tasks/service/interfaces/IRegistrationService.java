package team.exlab.tasks.service.interfaces;

import org.springframework.http.ResponseEntity;
import team.exlab.tasks.service.dto.user.UserDto;
import team.exlab.tasks.service.exception.InviteException;
import team.exlab.tasks.service.exception.RegistrationException;

public interface IRegistrationService {
    ResponseEntity<?> createNewUser(String workspaceId, String uniqueIdentifier,
                                    UserDto newUserDto) throws RegistrationException, InviteException;

    ResponseEntity<?> getRegistrationCredentials(String workspaceId, String uniqueIdentifier) throws RegistrationException, InviteException;
}
