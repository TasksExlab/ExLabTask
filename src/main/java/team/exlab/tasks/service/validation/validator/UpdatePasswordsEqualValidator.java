package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.validation.validator.annotation.UpdatePasswordsEqual;

public class UpdatePasswordsEqualValidator
        implements ConstraintValidator<UpdatePasswordsEqual, ChangePasswordUserDtoRequest> {

    @Override
    public boolean isValid(ChangePasswordUserDtoRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getPassword().equals(request.getPasswordConfirm());
    }
}
