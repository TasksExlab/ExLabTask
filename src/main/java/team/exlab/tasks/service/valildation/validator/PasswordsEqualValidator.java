package team.exlab.tasks.service.valildation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.valildation.validator.annotation.PasswordsEqual;

public class PasswordsEqualValidator
        implements ConstraintValidator<PasswordsEqual, ChangePasswordUserDtoRequest> {

    @Override
    public boolean isValid(ChangePasswordUserDtoRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getPassword().equals(request.getPasswordConfirm());
    }
}
