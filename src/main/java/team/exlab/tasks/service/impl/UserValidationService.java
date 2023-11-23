package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.interfaces.IUserValidationService;

@Service
@RequiredArgsConstructor
public class UserValidationService implements IUserValidationService {

    @Override
    public void validateChangePassword(ChangePasswordUserDtoRequest request) throws MethodArgumentNotValidException {

        throw new MethodArgumentNotValidException(
                null,
                new BeanPropertyBindingResult(request.getPasswordConfirm(), "passwordConfirm")
        );
    }
}
