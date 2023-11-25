package team.exlab.tasks.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import team.exlab.tasks.service.validation.validator.annotation.CorrectInviteEmail;
import team.exlab.tasks.service.validation.validator.annotation.CorrectRole;

@Data
public class CreateInviteDto {
    @CorrectRole(message = "Роль не найдена")
    private String role;
    @CorrectInviteEmail(message = "Ссылка уже была отправлена ранее этому пользователю")
    @Pattern(regexp = ".*exlab@gmail\\.com")
    @NotEmpty
    private String email;
}
