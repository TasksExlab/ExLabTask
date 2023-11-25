package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import team.exlab.tasks.service.interfaces.IRoleValidationService;
import team.exlab.tasks.service.validation.validator.annotation.CorrectRole;

public class CorrectRoleValidator implements ConstraintValidator<CorrectRole, String> {
    @Autowired
    private IRoleValidationService roleValidationService;

    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        return roleValidationService.isRoleValid(role);
    }
}
