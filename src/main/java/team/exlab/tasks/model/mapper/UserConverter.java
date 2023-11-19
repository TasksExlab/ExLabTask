package team.exlab.tasks.model.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.model.dto.UserPresentDto;
import team.exlab.tasks.model.entity.InviteEntity;
import team.exlab.tasks.model.entity.UserEntity;

@RequiredArgsConstructor
@Component
public class UserConverter {

    private final ModelMapper modelMapper;

    public UserEntity convertUserEntityFromDto(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public UserDto convertFromUserEntityToDto(UserEntity user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto emailFromInviteToDto(InviteEntity invite) {
        return modelMapper.map(invite, UserDto.class);
    }

    public UserPresentDto convertFromUserEntityToUserPresentDto(UserEntity user) {
        return modelMapper.map(user, UserPresentDto.class);
    }
}
