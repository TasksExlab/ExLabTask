package team.exlab.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import team.exlab.tasks.model.entity.InviteEntity;
import team.exlab.tasks.model.entity.UserEntity;
import team.exlab.tasks.model.repository.InviteRepository;
import team.exlab.tasks.model.repository.UserRepository;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.user.CreateUserDtoRequest;
import team.exlab.tasks.service.dto.user.JwtResponse;
import team.exlab.tasks.service.exception.NotFoundException;
import team.exlab.tasks.service.interfaces.IInviteValidationService;
import team.exlab.tasks.service.interfaces.IRegistrationService;
import team.exlab.tasks.service.mapper.UserConverter;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService {
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;
    private final UserConverter userConverter;
    private final IJwtService jwtService;
    private final IInviteValidationService inviteValidationService;
    private final UserDetailsService userDetailsService;

    @Override
    public BaseResponse getRegistrationCredentials(String uniqueIdentifier) {
        InviteEntity invite = inviteRepository.getInviteEntityByUniqueIdentifier(uniqueIdentifier)
                .orElseThrow(() -> new NotFoundException(
                        "invite.not.found",
                        String.format("Приглашение (identifier = '%s') не найдено", uniqueIdentifier))
                );

        inviteValidationService.isInviteExpired(invite.getDateOfExpireInvite());
        inviteValidationService.isInviteActivated(invite.getIsActivated());

        return new BaseResponse(String.format("Пользователь (email = '%s') перешел по ссылке", invite.getEmail()));
    }

    public JwtResponse createNewUser(String uniqueIdentifier, CreateUserDtoRequest request) {
        InviteEntity invite = inviteRepository.getInviteEntityByEmailAndUniqueIdentifier(
                request.getEmail(),
                uniqueIdentifier
        ).orElseThrow(() -> new NotFoundException(
                "invite.not.found",
                String.format("Приглашение (email = '%s', identifier = '%s') не найдено", request.getEmail(), uniqueIdentifier))
        );

        inviteValidationService.isInviteExpired(invite.getDateOfExpireInvite());
        inviteValidationService.isInviteActivated(invite.getIsActivated());

        UserEntity user = userConverter.convertDtoToEntity(request);
        user.setPassword(userConverter.encodePassword(user.getPassword()));
        user.setRole(invite.getRole());
        user.addWorkspace(invite.getWorkspace());
        userRepository.save(user);

        invite.setIsActivated(true);
        inviteRepository.save(invite);

        return new JwtResponse(
                jwtService.generateToken(userDetailsService.loadUserByUsername(user.getEmail()))
        );
    }
}

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
