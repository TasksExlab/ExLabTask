package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.exlab.tasks.model.dto.NewInviteDto;
import team.exlab.tasks.service.invite_registration.impl.InviteServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class InviteController {

    private final InviteServiceImpl inviteService;


    @PreAuthorize("hasAnyAuthority('PM')")
    @PostMapping("/{workspaceId}/invite/send")
    public ResponseEntity<?> create(@PathVariable String workspaceId, @RequestBody NewInviteDto inviteDto) throws IOException {
        return inviteService.sendInvite(workspaceId, inviteDto);
    }
}
