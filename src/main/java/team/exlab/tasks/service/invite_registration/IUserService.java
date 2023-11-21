package team.exlab.tasks.service.invite_registration;

import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.model.dto.UserDtoResponse;

public interface IUserService {
    UserDtoResponse create(UserDto userDto);
}

