package team.exlab.tasks.service.interfaces;

import team.exlab.tasks.service.dto.CreateInviteDtoRequest;

import java.time.LocalDateTime;

public interface IInviteValidationService {
//    void validate(CreateInviteDtoRequest inviteDto);

    void isInviteExpired(LocalDateTime dateOfExpireInvite);

    void isInviteActivated(boolean isActivated);

    boolean isInviteRepeatsToUser(String userEmail);
}
