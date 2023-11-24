package team.exlab.tasks.service.interfaces;

import org.springframework.http.ResponseEntity;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.dto.user.UserDto;
import team.exlab.tasks.service.exception.InviteException;
import team.exlab.tasks.service.exception.RegistrationException;

public interface IRegistrationService {
    JwtResponse createNewUser(String uniqueIdentifier, CreateUserDtoRequest request);

    BaseResponse getRegistrationCredentials(String uniqueIdentifier);
}
