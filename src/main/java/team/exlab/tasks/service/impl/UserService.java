package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDto;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.exception.NotFoundException;
import team.exlab.tasks.service.interfaces.IUserService;
import team.exlab.tasks.service.interfaces.IUserValidationService;
import team.exlab.tasks.service.mapper.UserConverter;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService, IUserValidationService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public void create(UserDto userDto) {
        Optional.of(userDto)
                .map((user) -> {
                    user.setPassword(
                            user.getPassword()
                    );
                    return user;
                })
                .map(userConverter::convertUserEntityFromDto)
                .map(userRepository::save)
                .map(userConverter::convertFromUserEntityToDto)
                .orElseThrow();
    }

    @Override
    public void changePassword(String userEmail, ChangePasswordUserDtoRequest request) {
        userRepository.findByEmail(userEmail)
                .map(user -> {
                    user.setPassword(
                            userConverter.encodePassword(request.getPassword())
                    );
                    return user;
                });
    }

    @Override
    public UserDtoResponse getUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(userConverter::convertFromUserEntityToDto)
                .orElseThrow(() -> new NotFoundException(
                        "user.not.found",
                        String.format("Пользователь не найден email = '%s'", username))
                );
    }

    @Override
    public boolean isValidLoginEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isValidRegistrationEmail(String email) {
        return !userRepository.existsByEmail(email);
    }
}
