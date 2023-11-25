package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import team.exlab.tasks.service.interfaces.IInviteValidationService;
import team.exlab.tasks.service.validation.validator.annotation.CorrectInviteEmail;

public class CorrectInviteEmailValidator implements ConstraintValidator<CorrectInviteEmail, String> {
    @Autowired
    private IInviteValidationService inviteValidationService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return inviteValidationService.isInviteRepeatsToUser(email);
    }
}
