package team.exlab.tasks.service.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginUserDtoRequest {
    @NotEmpty(message = "Заполните поле \"Электронная почта\"")
    String email;
    @NotEmpty(message = "Введите пароль")
    String password;
}
