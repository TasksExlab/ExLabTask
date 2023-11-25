package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDtoResponse;

public interface IUserService {
    void changePassword(String username, ChangePasswordUserDtoRequest request);

    UserDtoResponse getUserByUsername(String username);
}

