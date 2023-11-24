package team.exlab.tasks.service.interfaces;

import org.springframework.web.bind.MethodArgumentNotValidException;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDto;
import team.exlab.tasks.service.dto.user.UserDtoResponse;

public interface IUserService {
    void create(UserDto userDto);
    void changePassword(String username, ChangePasswordUserDtoRequest request);
    UserDtoResponse getUserByUsername(String username);
}

