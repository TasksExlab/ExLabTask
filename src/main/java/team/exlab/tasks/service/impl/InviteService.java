package team.exlab.tasks.service.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team.exlab.tasks.model.enam.UserRole;
import team.exlab.tasks.model.entity.InviteEntity;
import team.exlab.tasks.model.repository.InviteRepository;
import team.exlab.tasks.model.repository.RoleRepository;
import team.exlab.tasks.model.repository.WorkspaceRepository;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.NewInviteDto;
import team.exlab.tasks.service.interfaces.IEmailService;
import team.exlab.tasks.service.interfaces.IInviteService;
import team.exlab.tasks.service.mapper.InviteConverter;
import team.exlab.tasks.util.MailTemplate;

import java.io.IOException;
import java.time.LocalDateTime;

import static team.exlab.tasks.util.MessagesConstants.INVITATION_TO_THE_EXLAB_WORKSPACE;

@Slf4j
@Service
@RequiredArgsConstructor
public class InviteService implements IInviteService {


    private final InviteRepository inviteRepository;
    private final InviteConverter inviteConverter;

    private final WorkspaceRepository workspaceRepository;
    private final RoleRepository roleRepository;

    private final IEmailService emailService;

    private final ValidationService validationService;

    public InviteEntity create(NewInviteDto inviteDto) {
        return inviteRepository.save(inviteConverter.convertInviteEntityFromDto(inviteDto));
    }

    @Override
    public ResponseEntity<?> sendInvite(String workspaceId, NewInviteDto newInviteDto)
            throws IOException {

        if (validationService.isExistWorkspace(workspaceId)
            && validationService.isExistRole(newInviteDto.getRole())
            && validationService.isExistUserInInvites(newInviteDto.getEmail())
//                && validationService.isExistUser(newInviteDto.getEmail())
//                && !validationService.isExistUserInWorkspace(newInviteDto.getWorkspace().getId(),
//                newInviteDto.getEmail(), null)
        ) {

            return
                    ResponseEntity.ok(
                            new BaseResponse(
                                    "Ранее ссылка уже была отправлена пользователю с таким email " + newInviteDto.getEmail()));
        } else {
            InviteEntity invite = inviteConverter.convertInviteEntityFromDto(newInviteDto);
            setLinkLifeTime(invite);
            setInviteUniqueIdentifier(invite);
            invite.setWorkspace(workspaceRepository.findById(Long.valueOf(workspaceId)).get());
            invite.setRole(roleRepository.findByRole(UserRole.valueOf(newInviteDto.getRole().name())).get());

            String inviteUrl =
                    "http://localhost:8080/registration/workspace-invited/"
                    + invite.getWorkspace().getId() + "/registration-new-user/" + invite.getUniqueIdentifier();

            String htmlBody = MailTemplate.buildEmail(inviteUrl);

            try {
                emailService.sendMessageWithAttachment(invite.getEmail(),
                        INVITATION_TO_THE_EXLAB_WORKSPACE, htmlBody);
                inviteRepository.save(invite);
                return ResponseEntity.ok(new BaseResponse(
                        "Приглашение для нового пользователя " + newInviteDto.getEmail()
                        + " успешно отправлено!"));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setLinkLifeTime(InviteEntity invite) {
        LocalDateTime dateTimeOfInvite = LocalDateTime.now();
        invite.setDateOfExpireInvite(dateTimeOfInvite.plusDays(7));
    }

    private void setInviteUniqueIdentifier(InviteEntity invite) {
        invite.setUniqueIdentifier(RandomStringUtils.randomAlphanumeric(40));
    }
}
