package team.exlab.tasks.service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDtoRequest {
    @Pattern(regexp = "^[А-ЯA-Z][а-яёa-z]*$",
            message = "Введите, пожалуйста, ваше имя")
    @NotEmpty(message = "Поле обязательно для заполнения")
    String name;

    @Pattern(regexp = "^[А-ЯA-Z][а-яёa-z]*$",
            message = "Введите, пожалуйста, вашу фамилию")
    @NotEmpty(message = "Поле обязательно для заполнения")
    String surname;

    @Email(message = "Введите, пожалуйста, адрес вашей электронной почты")
    @NotEmpty(message = "Поле обязательно для заполнения")
    String email;

    @Pattern(regexp = ".{8,}",
            message = "Недостаточная длина пароля. Пароль должен содержать не менее 8 символов")
    @Pattern(regexp = "[a-zA-Z0-9]+",
            message = "Использование недопустимых знаков")
    @NotEmpty(message = "Поле обязательно для заполнения")
    String password;

    @NotEmpty(message = "Поле обязательно для заполнения")
    String passwordConfirm;
}
