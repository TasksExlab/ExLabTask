package team.exlab.tasks.service.validation.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import team.exlab.tasks.service.validation.validator.UniqueWorkspaceNameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueWorkspaceNameValidator.class)
public @interface UniqueWorkspaceName {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
