package team.exlab.tasks.service.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import team.exlab.tasks.service.valildation.validator.annotation.PasswordsEqual;

@Value
@Builder
@PasswordsEqual
public class ChangePasswordUserDtoRequest {
    @Pattern(regexp = ".{8,}",
            message = "Недостаточная длина пароля. Пароль должен содержать не менее 8 символов")
    @Pattern(regexp = "[a-zA-Z0-9]+",
            message = "Использование недопустимых знаков")
    @NotEmpty(message = "Поле обязательно для заполнения")
    String password;
    @NotEmpty(message = "Поле обязательно для заполнения")
    String passwordConfirm;
}
