package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.validation.validator.annotation.RegPasswordsEqual;

public class RegPasswordsEqualValidator
        implements ConstraintValidator<RegPasswordsEqual, CreateUserDtoRequest> {
    private String fieldName;

    @Override
    public void initialize(RegPasswordsEqual constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(CreateUserDtoRequest request, ConstraintValidatorContext context) {
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
