package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import team.exlab.tasks.service.dto.user.LoginUserDtoRequest;
import team.exlab.tasks.service.validation.validator.annotation.CorrectLoginData;

public class CorrectLoginDataValidator implements ConstraintValidator<CorrectLoginData, LoginUserDtoRequest> {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean isValid(LoginUserDtoRequest request, ConstraintValidatorContext constraintValidatorContext) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return false;
        }
        return true;
    }
}
