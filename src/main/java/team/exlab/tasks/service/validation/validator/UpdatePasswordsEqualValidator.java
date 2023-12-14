package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.validation.validator.annotation.UpdatePasswordsEqual;

public class UpdatePasswordsEqualValidator
        implements ConstraintValidator<UpdatePasswordsEqual, ChangePasswordUserDtoRequest> {
    private String fieldName;

    @Override
    public void initialize(UpdatePasswordsEqual constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(ChangePasswordUserDtoRequest request, ConstraintValidatorContext context) {
        var result = request.getPassword().equals(request.getPasswordConfirm());
        if (!result) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
        }
        return result;
    }
}
