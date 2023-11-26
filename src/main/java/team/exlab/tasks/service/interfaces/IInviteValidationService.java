package team.exlab.tasks.service.interfaces;

import java.time.LocalDateTime;

public interface IInviteValidationService {
    void isInviteExpired(LocalDateTime dateOfExpireInvite);

    void isInviteActivated(boolean isActivated);

    boolean isInviteRepeatsToUser(String userEmail);
}
