package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.dto.user.LoginUserDtoRequest;
import team.exlab.tasks.service.interfaces.IUserValidationService;

@Service
@RequiredArgsConstructor
@Transactional
public class UserValidationService implements IUserValidationService {
    private final UserRepository userRepository;

    @Override
    public boolean isValidLoginEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isValidRegistrationEmail(String email) {
        return !userRepository.existsByEmail(email);
    }
}
