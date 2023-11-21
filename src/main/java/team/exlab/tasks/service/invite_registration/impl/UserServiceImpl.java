package team.exlab.tasks.service.invite_registration.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.model.dto.UserDtoResponse;
import team.exlab.tasks.model.mapper.UserConverter;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.service.invite_registration.IUserService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public UserDtoResponse create(UserDto userDto) {
        return Optional.of(userDto)
                .map((user) -> {
                    userConverter.encodePassword(user);
                    return user;
                })
                .map(userConverter::convertUserEntityFromDto)
                .map(userRepository::save)
                .map(userConverter::convertFromUserEntityToDto)
                .orElseThrow();
    }
}
