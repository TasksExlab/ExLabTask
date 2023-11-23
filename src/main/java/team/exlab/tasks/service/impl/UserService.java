package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDto;
import team.exlab.tasks.service.interfaces.IUserService;
import team.exlab.tasks.service.mapper.UserConverter;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserValidationService userValidationService;

    @Override
    public void create(UserDto userDto) {
        Optional.of(userDto)
                .map((user) -> {
                    user.setPassword(
                            userConverter.encodePassword(user.getPassword())
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
        //userValidationService.validateChangePassword(request);
        userRepository.findByEmail(userEmail)
                .map(user -> {
                    user.setPassword(
                            userConverter.encodePassword(request.getPassword())
                    );
                    return user;
                });
    }

    public boolean isExistUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
