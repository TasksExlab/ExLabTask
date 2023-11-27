package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.CreateInviteDto;

public interface IInviteService {
    BaseResponse sendInvite(Long workspaceId, CreateInviteDto createInviteDto);
}
