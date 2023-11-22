package team.exlab.tasks.model.valildation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.model.valildation.validator.annotation.PasswordsEqual;

public class PasswordsEqualValidator
        implements ConstraintValidator<PasswordsEqual, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getPasswordConfirm());
    }
}
