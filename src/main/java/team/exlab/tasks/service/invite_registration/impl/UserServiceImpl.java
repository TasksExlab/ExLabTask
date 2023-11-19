package team.exlab.tasks.service.invite_registration.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.model.entity.UserEntity;
import team.exlab.tasks.model.mapper.UserConverter;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.service.invite_registration.IUserService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    @Override
    public Optional<UserDto> create(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity newUser = userConverter.convertUserEntityFromDto(user);
        return Optional.of(userConverter.convertFromUserEntityToDto(userRepository.save(newUser)));
    }
}
