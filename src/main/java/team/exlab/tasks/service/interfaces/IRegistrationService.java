package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.dto.user.JwtResponse;

public interface IRegistrationService {
    JwtResponse createNewUser(String uniqueIdentifier, CreateUserDtoRequest request);

    BaseResponse getRegistrationCredentials(String uniqueIdentifier);
}
