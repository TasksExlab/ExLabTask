package team.exlab.tasks.service.interfaces;

import org.springframework.web.bind.MethodArgumentNotValidException;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;

public interface IUserValidationService {
    void validateChangePassword(ChangePasswordUserDtoRequest request) throws MethodArgumentNotValidException;
//    void validateRegistration(CreateUserDtoRequest request);
}
