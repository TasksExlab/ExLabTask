package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.exlab.tasks.model.repository.InviteRepository;
import team.exlab.tasks.service.exception.BadRequestException;
import team.exlab.tasks.service.interfaces.IInviteValidationService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InviteValidationService implements IInviteValidationService {
    private final InviteRepository inviteRepository;

    @Override
    public void isInviteExpired(LocalDateTime dateOfExpireInvite) {
        if (LocalDateTime.now().isAfter(dateOfExpireInvite)) {
            throw new BadRequestException(
                    "link.has.expired",
                    "Время действия ссылки истекло"
            );
        }
    }

    @Override
    public void isInviteActivated(boolean isActivated) {
        if (isActivated) {
            throw new BadRequestException(
                    "link.has.activated.yet",
                    "Ссылка уже была активирована ранее"
            );
        }
    }

    @Override
    public boolean isInviteRepeatsToUser(String userEmail) {
        return !inviteRepository.existsByEmail(userEmail);
    }
}
