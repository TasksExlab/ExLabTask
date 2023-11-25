package team.exlab.tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.exlab.tasks.service.dto.BaseResponse;
import team.exlab.tasks.service.dto.CreateInviteDto;
import team.exlab.tasks.service.interfaces.IInviteService;

import static team.exlab.tasks.util.UrlPathUtil.API;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
public class InviteController {
    private final IInviteService inviteService;

    @PreAuthorize("hasAnyAuthority('PM', 'ADMIN')")
    @PostMapping(value = "workspaces/{workspaceId}/invite")
    public ResponseEntity<BaseResponse> sendInvite(@PathVariable Long workspaceId,
                                                   @Validated @RequestBody CreateInviteDto inviteDto) {
        return new ResponseEntity<>(
                inviteService.sendInvite(workspaceId, inviteDto),
                HttpStatus.OK
        );
    }
}
