package team.exlab.tasks.service.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.entity.User;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.dto.user.UserDtoResponse;

@RequiredArgsConstructor
@Component
public class UserConverter {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public User convertDtoToEntity(CreateUserDtoRequest userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDtoResponse convertEntityToDto(User user) {
        return modelMapper.map(user, UserDtoResponse.class);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDtoResponse.class)
                .addMappings(mapper -> mapper.map(src -> src.getRole().getDescription(),
                        UserDtoResponse::setRole));
    }
}
