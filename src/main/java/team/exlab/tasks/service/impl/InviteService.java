package team.exlab.tasks.service.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.exlab.tasks.model.enam.UserRole;
import team.exlab.tasks.model.entity.Invite;
import team.exlab.tasks.model.repository.InviteRepository;
import team.exlab.tasks.model.repository.RoleRepository;
import team.exlab.tasks.model.repository.WorkspaceRepository;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.CreateInviteDtoRequest;
import team.exlab.tasks.service.exception.NotFoundException;
import team.exlab.tasks.service.interfaces.IEmailService;
import team.exlab.tasks.service.interfaces.IInviteService;
import team.exlab.tasks.service.mapper.InviteConverter;
import team.exlab.tasks.util.MailTemplate;

import java.io.IOException;

import static team.exlab.tasks.util.MessagesConstants.INVITATION_TO_THE_EXLAB_WORKSPACE;

@Service
@RequiredArgsConstructor
public class InviteService implements IInviteService {
    private final InviteRepository inviteRepository;
    private final WorkspaceRepository workspaceRepository;
    private final RoleRepository roleRepository;
    private final InviteConverter inviteConverter;
    private final IEmailService emailService;

    @Override
    @Transactional
    public BaseResponse sendInvite(Long workspaceId, CreateInviteDtoRequest createInviteDtoRequest) {
        var workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new NotFoundException(
                        "workspace.not.found",
                        String.format("Рабочее пространство (id = '%s') не найдено", workspaceId)
                ));

        Invite invite = inviteConverter.convertDtoToEntity(createInviteDtoRequest);
        invite.setLinkLifeTime();
        invite.setInviteUniqueIdentifier();
        invite.setWorkspace(workspace);
        invite.setRole(roleRepository.findByRole(UserRole.find(createInviteDtoRequest.getRoleName()).get()).get());

        String inviteUrl = "http://localhost:8080/api/v1/registration/" + invite.getUniqueIdentifier();
        String htmlBody = MailTemplate.buildEmail(inviteUrl);

        try {
            emailService.sendMessageWithAttachment(
                    invite.getEmail(),
                    INVITATION_TO_THE_EXLAB_WORKSPACE,
                    htmlBody
            );

            inviteRepository.save(invite);

            return new BaseResponse(
                    String.format("Приглашение для пользователя (email = '%s') успешно отправлено!",
                            createInviteDtoRequest.getEmail())
            );
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
