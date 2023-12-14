package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import team.exlab.tasks.service.dto.user.LoginUserDtoRequest;
import team.exlab.tasks.service.validation.validator.annotation.CorrectLoginData;
import team.exlab.tasks.service.validation.validator.annotation.UpdatePasswordsEqual;

public class CorrectLoginDataValidator implements ConstraintValidator<CorrectLoginData, LoginUserDtoRequest> {
    @Autowired
    private AuthenticationManager authenticationManager;
    private String fieldName;

    @Override
    public void initialize(CorrectLoginData constraintAnnotation) {
            this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(LoginUserDtoRequest request, ConstraintValidatorContext context) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
