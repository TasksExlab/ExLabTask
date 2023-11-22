package team.exlab.tasks.model.dto;

import lombok.Data;
import team.exlab.tasks.model.enam.UserRole;

@Data
public class NewInviteDto {

    private UserRole role;
    private String email;
}
