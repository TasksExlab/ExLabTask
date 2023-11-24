package team.exlab.tasks.service.interfaces;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.CreateInviteDto;
import team.exlab.tasks.service.exception.InviteException;

import java.io.IOException;

public interface IInviteService {
    BaseResponse sendInvite(Long workspaceId, CreateInviteDto createInviteDto);
}
