package team.exlab.tasks.model.valildation.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import team.exlab.tasks.model.valildation.validator.PasswordsEqualValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsEqualValidator.class)
public @interface PasswordsEqual {
    String message() default "{passwords.equal}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
