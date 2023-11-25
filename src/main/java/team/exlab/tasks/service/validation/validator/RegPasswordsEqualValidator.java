package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.validation.validator.annotation.RegPasswordsEqual;
import team.exlab.tasks.service.validation.validator.annotation.UpdatePasswordsEqual;

public class RegPasswordsEqualValidator
        implements ConstraintValidator<RegPasswordsEqual, CreateUserDtoRequest> {

    @Override
    public boolean isValid(CreateUserDtoRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getPassword().equals(request.getPasswordConfirm());
    }
}
