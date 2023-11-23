package team.exlab.tasks.service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.service.dto.NewInviteDto;
import team.exlab.tasks.model.entity.InviteEntity;

@RequiredArgsConstructor
@Component
public class InviteConverter {

    private final ModelMapper modelMapper;

    public InviteEntity convertInviteEntityFromDto(NewInviteDto invite) {
        return modelMapper.map(invite, InviteEntity.class);
    }
}
