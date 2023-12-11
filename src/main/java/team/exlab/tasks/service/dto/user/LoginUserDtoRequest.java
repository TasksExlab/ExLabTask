package team.exlab.tasks.service.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.exlab.tasks.service.validation.validator.annotation.CorrectEmail;
import team.exlab.tasks.service.validation.validator.annotation.CorrectLoginData;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CorrectLoginData(message = "Пользователя с такими данными не существует", fieldName = "password")
public class LoginUserDtoRequest {
    @CorrectEmail(message = "Неверный адрес электронной почты")
    @NotEmpty(message = "Заполните поле 'Электронная почта'")
    private String email;
    @NotEmpty(message = "Введите пароль")
    private String password;
}
