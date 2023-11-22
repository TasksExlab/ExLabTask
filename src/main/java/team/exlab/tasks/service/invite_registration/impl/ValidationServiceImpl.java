package team.exlab.tasks.service.invite_registration.impl;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.exlab.tasks.model.enam.UserRole;
import team.exlab.tasks.model.repository.InviteRepository;
import team.exlab.tasks.model.repository.RoleRepository;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.model.repository.WorkspaceRepository;
import team.exlab.tasks.exception.RegistrationException;
import team.exlab.tasks.exception.WorkspaceException;

import java.time.LocalDateTime;

import static team.exlab.tasks.util.MessagesConstants.LINK_IS_NO_LONGER_AVAILABLE;
import static team.exlab.tasks.util.MessagesConstants.NECESSARY_TO_CONFIRM_CONSENT_TO_THE_PROCESSING_OF_PERSONAL_DATA;
import static team.exlab.tasks.util.MessagesConstants.THERE_IS_NO_SUCH_ROLE;
import static team.exlab.tasks.util.MessagesConstants.WORKSPACE_ID_DONT_MATCH;
import static team.exlab.tasks.util.MessagesConstants.WORKSPACE_WITH_THE_SPECIFIED_ID_DOES_NOT_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
public
class ValidationServiceImpl {


    private final InviteRepository inviteRepository;

    private final WorkspaceRepository workspaceRepository;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public boolean isExistWorkspace(String workspaceId) {
        boolean isExistWorkspace = workspaceRepository.existsById(Long.valueOf(workspaceId));
        if (!isExistWorkspace) {
            throw new NoResultException(WORKSPACE_WITH_THE_SPECIFIED_ID_DOES_NOT_EXIST);
        }
        return isExistWorkspace;
    }

    public boolean isExistRole(UserRole role) {
        boolean isExistRole = roleRepository.existsByRole(role);
        if (!isExistRole) {
            throw new NoResultException(THERE_IS_NO_SUCH_ROLE);
        }
        return isExistRole;
    }

    /**
     * Проверяет существование пользователя в базе данных, в случае совпадения и возврата
     * положительного результата, пробрасывает исключение с передаваемым текстом
     *
     * @param email
     * @param message
     * @return false
     * @throws RegistrationException
     */
    public boolean isExistUser(String email, String message) throws RegistrationException {
        if (userRepository.existsByEmail(email)) {
            throw new RegistrationException(message);
        }
        return true;
    }

    /**
     * Проверяет существование пользователя в базе данных, в случае совпадения возвращает true, в иных
     * случаях false
     *
     * @param email
     * @return boolean
     */
    public boolean isExistUser(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isExistUserInInvites(String email) {
        return inviteRepository.existsByEmail(email);
    }


//    public boolean isExistUserInWorkspace(Long workspaceId, String email, InviteEntity invite)
//            throws InviteException {
//        boolean isExistUserInWorkspace = inviteRepository
//                .existsByWorkspaceAndEmail(
//                        workspaceId,
//                        email);
//        if (isExistUserInWorkspace) {
//            Optional.ofNullable(invite).ifPresent(inv -> {
//                inv.setActivated(true);
//                inviteRepository.save(inv);
//            });
//            throw new InviteException(
//                    "Пользователь с email " + email + " уже существует в этом пространстве");
//        }
//        return false;
//    }

    public boolean isExpiredDateOfInvite(LocalDateTime dateTimeFromInvite) throws RegistrationException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (currentDateTime.compareTo(dateTimeFromInvite) >= 1) {
            throw new RegistrationException(LINK_IS_NO_LONGER_AVAILABLE);
        }
        return true;
    }

    public boolean isProcessingAllowed(boolean isAgree) throws RegistrationException {
        if (!isAgree) {
            throw new RegistrationException(NECESSARY_TO_CONFIRM_CONSENT_TO_THE_PROCESSING_OF_PERSONAL_DATA);
        }
        return isAgree;
    }

    public boolean isEqualWorkspaceIdFromLinkAndWorkspaceIdFromInvite(String linkWsId, String inviteWsId) throws WorkspaceException {
        boolean isEqualLinkWsIdAndInviteWsId = linkWsId == inviteWsId.intern();
        if (!isEqualLinkWsIdAndInviteWsId) {
            throw new WorkspaceException(WORKSPACE_ID_DONT_MATCH);
        }
        return isEqualLinkWsIdAndInviteWsId;
    }
}
