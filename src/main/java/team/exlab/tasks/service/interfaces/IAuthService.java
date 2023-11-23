package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.dto.user.LoginUserDtoRequest;

public interface IAuthService {
    JwtResponse createAuthToken(LoginUserDtoRequest request);
}
