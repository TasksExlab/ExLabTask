package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.ChangePasswordUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDtoResponse;
import team.exlab.tasks.service.interfaces.IUserService;
import team.exlab.tasks.service.interfaces.IUserValidationService;
import team.exlab.tasks.service.mapper.UserConverter;
import team.exlab.tasks.util.MessagesConstants;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    @Transactional
    public BaseResponse changePassword(String userEmail, ChangePasswordUserDtoRequest request) {
        userRepository.findByEmail(userEmail)
                .map(user -> {
                    user.setPassword(
                            userConverter.encodePassword(request.getPassword())
                    );
                    return user;
                });
        return new BaseResponse(MessagesConstants.SUCCESSFUL_CHANGE_PASSWORD);
    }

    @Override
    public UserDtoResponse getUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .map(userConverter::convertEntityToDto)
                .orElseThrow();
    }
}
