package team.exlab.tasks.service.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import team.exlab.tasks.service.validation.validator.annotation.CorrectEmail;
import team.exlab.tasks.service.validation.validator.annotation.CorrectLoginData;

@Data
@CorrectLoginData(message = "Пользователя с такими данными не существует")
public class LoginUserDtoRequest {
    @NotEmpty(message = "Заполните поле 'Электронная почта'")
    @CorrectEmail(message = "Неверный адрес электронной почты")
    private String email;
    @NotEmpty(message = "Введите пароль")
    private String password;
}
