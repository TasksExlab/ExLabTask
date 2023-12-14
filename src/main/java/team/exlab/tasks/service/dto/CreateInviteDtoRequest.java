package team.exlab.tasks.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.exlab.tasks.service.validation.group.CommonValidationGroup;
import team.exlab.tasks.service.validation.validator.annotation.CorrectInviteEmail;
import team.exlab.tasks.service.validation.validator.annotation.CorrectRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInviteDtoRequest {
    @CorrectRole(message = "Роль не найдена",
            groups = CommonValidationGroup.class)
    private String roleName;
    @CorrectInviteEmail(message = "Ссылка уже была отправлена ранее этому пользователю")
    @Pattern(regexp = ".*exlab@gmail\\.com",
            groups = CommonValidationGroup.class)
    @NotEmpty(message = "Введите адрес электронной почты",
            groups = CommonValidationGroup.class)
    private String email;
}
