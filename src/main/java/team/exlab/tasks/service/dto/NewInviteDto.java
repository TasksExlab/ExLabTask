package team.exlab.tasks.service.dto;

import lombok.Data;
import team.exlab.tasks.model.enam.UserRole;

@Data
public class NewInviteDto {

    private UserRole role;
    private String email;
}
