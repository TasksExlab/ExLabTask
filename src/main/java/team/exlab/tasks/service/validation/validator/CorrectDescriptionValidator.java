package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import team.exlab.tasks.service.validation.validator.annotation.CorrectDescription;

public class CorrectDescriptionValidator implements ConstraintValidator<CorrectDescription, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.isEmpty() || s.matches("[a-zA-Zа-яА-Я0-9.,!?();: ]+");
    }
}
