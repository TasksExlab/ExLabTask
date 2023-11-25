package team.exlab.tasks.service.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import team.exlab.tasks.service.validation.validator.annotation.RegPasswordsEqual;
import team.exlab.tasks.service.validation.validator.annotation.UniqueEmail;

@Data
@RegPasswordsEqual(message = "Пароли не совпадают")
public class CreateUserDtoRequest {
    @Pattern(regexp = "^[А-ЯA-Z][а-яёa-z]*$",
            message = "Введите, пожалуйста, ваше имя")
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String name;

    @Pattern(regexp = "^[А-ЯA-Z][а-яёa-z]*$",
            message = "Введите, пожалуйста, вашу фамилию")
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String surname;

    @Email(message = "Введите, пожалуйста, адрес вашей электронной почты")
    @NotEmpty(message = "Поле обязательно для заполнения")
    @UniqueEmail(message = "Пользователь с такими данными уже зарегистрирован")
    private String email;

    @Pattern(regexp = ".{8,}",
            message = "Недостаточная длина пароля. Пароль должен содержать не менее 8 символов")
    @Pattern(regexp = "[a-zA-Z0-9]+",
            message = "Использование недопустимых знаков")
    @NotEmpty(message = "Поле обязательно для заполнения")
    private String password;

    @NotEmpty(message = "Поле обязательно для заполнения")
    private String passwordConfirm;
    @AssertTrue(message = "Необходимо подтвердить согласие на обработку персональных данных")
    private Boolean isUserAgreeToProcessPersonalData;

    @JsonCreator
    public CreateUserDtoRequest(@JsonProperty("name") String name,
                                @JsonProperty("surname") String surname,
                                @JsonProperty("email") String email,
                                @JsonProperty("password") String password,
                                @JsonProperty("passwordConfirm") String passwordConfirm,
                                @JsonProperty("isUserAgreeToProcessPersonalData") Boolean isUserAgreeToProcessPersonalData) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.isUserAgreeToProcessPersonalData = isUserAgreeToProcessPersonalData;
    }
}
