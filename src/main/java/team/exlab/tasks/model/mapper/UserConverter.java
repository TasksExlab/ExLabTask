package team.exlab.tasks.model.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.model.dto.UserDtoResponse;
import team.exlab.tasks.model.dto.UserPresentDto;
import team.exlab.tasks.model.entity.InviteEntity;
import team.exlab.tasks.model.entity.UserEntity;

@RequiredArgsConstructor
@Component
public class UserConverter {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserEntity convertUserEntityFromDto(UserDto userDto) {
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

    public void encodePassword(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }
}
