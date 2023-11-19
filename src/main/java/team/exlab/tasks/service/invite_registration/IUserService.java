package team.exlab.tasks.service.invite_registration;

import team.exlab.tasks.model.dto.UserDto;

import java.util.Optional;

public interface IUserService {
    Optional<UserDto> create(UserDto userDto);
}

