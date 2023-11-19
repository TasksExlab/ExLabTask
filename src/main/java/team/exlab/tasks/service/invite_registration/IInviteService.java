package team.exlab.tasks.service.invite_registration;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import team.exlab.tasks.model.dto.NewInviteDto;
import team.exlab.tasks.exeption.InviteException;

import java.io.IOException;

public interface IInviteService {
    ResponseEntity<?> sendInvite(String workspaceId, NewInviteDto newInviteDto) throws InviteException, MessagingException, IOException;
}
