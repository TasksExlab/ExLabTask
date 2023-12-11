package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.CreateInviteDtoRequest;

public interface IInviteService {
    BaseResponse sendInvite(Long workspaceId, CreateInviteDtoRequest createInviteDtoRequest);
}
