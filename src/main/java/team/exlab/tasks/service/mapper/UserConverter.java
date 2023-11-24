package team.exlab.tasks.service.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.entity.InviteEntity;
import team.exlab.tasks.model.entity.UserEntity;
import team.exlab.tasks.service.dto.UserPresentDto;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDto;
import team.exlab.tasks.service.dto.user.UserDtoResponse;

@RequiredArgsConstructor
@Component
public class UserConverter {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserEntity convertUserEntityFromDto(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
    public UserEntity convertUserEntityFromDto(CreateUserDtoRequest userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public UserDtoResponse convertFromUserEntityToDto(UserEntity user) {
        return modelMapper.map(user, UserDtoResponse.class);
    }

    public UserDto emailFromInviteToDto(InviteEntity invite) {
        return modelMapper.map(invite, UserDto.class);
    }

    public UserPresentDto convertFromUserEntityToUserPresentDto(UserEntity user) {
        return modelMapper.map(user, UserPresentDto.class);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(UserEntity.class, UserDtoResponse.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getRole().getDescription(),
                            UserDtoResponse::setRole);
                });
    }
}
