package team.exlab.tasks.service.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.exlab.tasks.service.validation.group.DBValidationGroup;
import team.exlab.tasks.service.validation.group.CommonValidationGroup;
import team.exlab.tasks.service.validation.validator.annotation.RegPasswordsEqual;
import team.exlab.tasks.service.validation.validator.annotation.UniqueEmail;

@Data
@NoArgsConstructor
@RegPasswordsEqual(message = "Пароли не совпадают",
        fieldName = "passwordConfirm",
        groups = CommonValidationGroup.class)
public class CreateUserDtoRequest {
    @Pattern(regexp = "^[А-ЯA-Z][а-яёa-z]*$",
            message = "Введите, пожалуйста, ваше имя",
            groups = CommonValidationGroup.class)
    @NotEmpty(message = "Поле обязательно для заполнения",
            groups = CommonValidationGroup.class)
    private String name;

    @Pattern(regexp = "^[А-ЯA-Z][а-яёa-z]*$",
            message = "Введите, пожалуйста, вашу фамилию",
            groups = CommonValidationGroup.class)
    @NotEmpty(message = "Поле обязательно для заполнения",
            groups = CommonValidationGroup.class)
    private String surname;

    @Email(message = "Введите, пожалуйста, адрес вашей электронной почты",
            groups = CommonValidationGroup.class)
    @UniqueEmail(message = "Пользователь с такими данными уже зарегистрирован",
            groups = DBValidationGroup.class)
    @NotEmpty(message = "Поле обязательно для заполнения",
            groups = CommonValidationGroup.class)
    private String email;

    @Pattern(regexp = ".{8,}",
            message = "Недостаточная длина пароля. Пароль должен содержать не менее 8 символов",
            groups = CommonValidationGroup.class)
    @Pattern(regexp = "[a-zA-Z0-9]+",
            message = "Использование недопустимых знаков",
            groups = CommonValidationGroup.class)
    @NotEmpty(message = "Поле обязательно для заполнения",
            groups = CommonValidationGroup.class)
    private String password;

    @NotEmpty(message = "Поле обязательно для заполнения",
            groups = CommonValidationGroup.class)
    private String passwordConfirm;
    @AssertTrue(message = "Необходимо подтвердить согласие на обработку персональных данных",
            groups = CommonValidationGroup.class)
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
