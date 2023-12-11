package team.exlab.tasks.service.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.exlab.tasks.service.validation.group.DBValidationGroup;
import team.exlab.tasks.service.validation.group.CommonValidationGroup;
import team.exlab.tasks.service.validation.validator.annotation.CorrectEmail;
import team.exlab.tasks.service.validation.validator.annotation.CorrectLoginData;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CorrectLoginData(message = "Пользователя с такими данными не существует",
        fieldName = "password",
        groups = DBValidationGroup.class)
public class LoginUserDtoRequest {
    @CorrectEmail(message = "Неверный адрес электронной почты",
            groups = DBValidationGroup.class)
    @NotEmpty(message = "Заполните поле 'Электронная почта'",
            groups = CommonValidationGroup.class)
    private String email;
    @NotEmpty(message = "Введите пароль",
            groups = CommonValidationGroup.class)
    private String password;
}
