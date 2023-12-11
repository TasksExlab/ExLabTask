package team.exlab.tasks.service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import team.exlab.tasks.model.entity.Invite;
import team.exlab.tasks.service.dto.CreateInviteDtoRequest;

@RequiredArgsConstructor
@Component
public class InviteConverter {
    private final ModelMapper modelMapper;

    public Invite convertDtoToEntity(CreateInviteDtoRequest invite) {
        return modelMapper.map(invite, Invite.class);
    }
}
