package team.exlab.tasks.service.interfaces;

public interface IUserValidationService {
//    void validate(ChangePasswordUserDtoRequest userDto);

//    void validate(CreateUserDtoRequest userDto);

//    void validate(LoginUserDtoRequest userDto);

    boolean isValidLoginEmail(String email);

    boolean isValidRegistrationEmail(String email);
}
