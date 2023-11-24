package team.exlab.tasks.service.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.enam.UserRole;
import team.exlab.tasks.model.entity.InviteEntity;
import team.exlab.tasks.model.repository.RoleRepository;
import team.exlab.tasks.service.dto.CreateInviteDto;

@RequiredArgsConstructor
@Component
public class InviteConverter {
    private final ModelMapper modelMapper;

    public InviteEntity convertInviteEntityFromDto(CreateInviteDto invite) {
        return modelMapper.map(invite, InviteEntity.class);
    }
}
