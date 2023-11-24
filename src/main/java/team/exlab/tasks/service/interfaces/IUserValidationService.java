package team.exlab.tasks.service.interfaces;

public interface IUserValidationService {
    boolean isValidLoginEmail(String email);
    boolean isValidRegistrationEmail(String email);
}
