package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.validation.validator.annotation.RegPasswordsEqual;

public class RegPasswordsEqualValidator
        implements ConstraintValidator<RegPasswordsEqual, CreateUserDtoRequest> {

    @Override
    public boolean isValid(CreateUserDtoRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getPassword().equals(request.getPasswordConfirm());
    }
}
