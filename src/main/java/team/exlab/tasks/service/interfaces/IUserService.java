package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDtoResponse;

public interface IUserService {
    BaseResponse changePassword(String username, ChangePasswordUserDtoRequest request);

    UserDtoResponse getUserByUsername(String username);
}

