package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import team.exlab.tasks.service.interfaces.IUserValidationService;
import team.exlab.tasks.service.validation.validator.annotation.CorrectEmail;

public class CorrectEmailValidator implements ConstraintValidator<CorrectEmail, String> {
    @Autowired
    private IUserValidationService userValidationService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userValidationService.isValidLoginEmail(email);
    }
}
