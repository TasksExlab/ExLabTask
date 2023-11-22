package team.exlab.tasks.service.invite_registration.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.exlab.tasks.api.response.BaseResponse;
import team.exlab.tasks.exception.InviteException;
import team.exlab.tasks.exception.RegistrationException;
import team.exlab.tasks.model.dto.UserDto;
import team.exlab.tasks.model.entity.InviteEntity;
import team.exlab.tasks.model.entity.UserEntity;
import team.exlab.tasks.model.mapper.UserConverter;
import team.exlab.tasks.model.repository.InviteRepository;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.model.repository.WorkspaceRepository;
import team.exlab.tasks.service.invite_registration.IRegistrationService;

import static team.exlab.tasks.util.MessagesConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements IRegistrationService {
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final ValidationServiceImpl validationService;
    private final WorkspaceRepository workspaceRepository;

    //    private final WorkspaceDetailsRepository workspaceDetailsRepository;
    @Override
    public ResponseEntity<?> getRegistrationCredentials(String workspaceId, String uniqueIdentifier)
            throws RegistrationException, InviteException {
        InviteEntity invite = inviteRepository.getInviteEntityByUniqueIdentifier(uniqueIdentifier)
                .orElseThrow(() -> new RegistrationException(new ChangeSetPersister.NotFoundException().getMessage()));

        if (validationService.isExistUser(invite.getEmail())) {
            throw new RegistrationException(USER_IS_ALREADY_REGISTERED);
        }

        if (validationService.isExistWorkspace(workspaceId)
            && (invite.getActivated()
                && validationService.isExpiredDateOfInvite(
                invite.getDateOfExpireInvite()))) {


            return ResponseEntity.ok(new BaseResponse("Пользователь с email (" + invite.getEmail() + ") перешел по ссылке"));
        }
        throw new InviteException(LINK_HAS_ALREADY_BEEN_ACTIVATED);
    }

    @Override
    public ResponseEntity<?> createNewUser(String workspaceId, String uniqueIdentifier,
                                           UserDto newUserDto)
            throws RegistrationException, InviteException {

        InviteEntity invite = inviteRepository.getInviteEntityByEmailAndUniqueIdentifier(
                newUserDto.getEmail(),
                uniqueIdentifier
        ).orElseThrow(() -> new InviteException(USER_WITH_THIS_EMAIL_OR_UNIQUE_ID_NOT_FOUND));

        if (validationService.isExistUser(
                newUserDto.getEmail(),
                USER_IS_ALREADY_REGISTERED)
            && validationService.isExistWorkspace(workspaceId)
            && invite.getActivated()
            && validationService.isExpiredDateOfInvite(invite.getDateOfExpireInvite())
            && validationService.isProcessingAllowed(
                newUserDto.isUserAgreeToProcessPersonalData())
            && validEqualityPasswords(newUserDto.getPassword(), newUserDto.getPasswordConfirm())) {

            UserEntity user = userConverter.convertUserEntityFromDto(newUserDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(invite.getRole());
            user.addWorkspace(invite.getWorkspace());
            userRepository.save(user);
            invite.setActivated(false);
            inviteRepository.save(invite);

//            WorkspaceDetailsId workspaceDetailsId = WorkspaceDetailsId
//                    .builder()
//                    .workspace(
//                            String.valueOf(workspaceRepository.getWorkspaceById(invite.getWorkspace().getId())))
//                    .user(user)
//                    .role(invite.getRole())
//                    .build();
//
//            WorkspaceDetails workspaceDetails = WorkspaceDetails.builder()
//                    .workspaceDetailsId(workspaceDetailsId)
//                    .build();
//            workspaceDetailsRepository.save(workspaceDetails);

            return ResponseEntity.ok(SUCCESSFUL_REGISTRATION);
        }
        throw new RegistrationException(PASSWORD_DONT_MATCH);
    }


    private boolean validEqualityPasswords(String pass, String pass2) {
        return pass.equals(pass2);
    }
}
