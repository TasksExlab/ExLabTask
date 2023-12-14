package team.exlab.tasks.service.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.exlab.tasks.service.validation.validator.annotation.UpdatePasswordsEqual;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UpdatePasswordsEqual(message = "Пароли не совпадают", fieldName = "passwordConfirm")
public class ChangePasswordUserDtoRequest {
    @Pattern(regexp = ".{8,}",
            message = "Недостаточная длина пароля. Пароль должен содержать не менее 8 символов")
    @Pattern(regexp = "[a-zA-Z0-9]+",
            message = "Использование недопустимых знаков")
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String password;
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String passwordConfirm;
}
