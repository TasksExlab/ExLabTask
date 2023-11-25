package team.exlab.tasks.service.validation.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import team.exlab.tasks.service.validation.validator.RegPasswordsEqualValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegPasswordsEqualValidator.class)
public @interface RegPasswordsEqual {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
