package team.exlab.tasks.service.interfaces;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import team.exlab.tasks.service.dto.NewInviteDto;
import team.exlab.tasks.service.exception.InviteException;

import java.io.IOException;

public interface IInviteService {
    ResponseEntity<?> sendInvite(String workspaceId, NewInviteDto newInviteDto) throws InviteException, MessagingException, IOException;
}
