package team.exlab.tasks.service.dto.workspace;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.exlab.tasks.service.validation.group.CommonValidationGroup;
import team.exlab.tasks.service.validation.group.DBValidationGroup;
import team.exlab.tasks.service.validation.validator.annotation.CorrectDescription;
import team.exlab.tasks.service.validation.validator.annotation.UniqueWorkspaceName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWorkspaceDtoRequest {
    @NotEmpty(message = "Введите название рабочего пространства",
            groups = CommonValidationGroup.class)
    @Size(min = 3, message = "Название рабочего пространства должно содержать хотя бы 3 символа",
            groups = CommonValidationGroup.class)
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9.,!?();: ]+",
            message = "Название должно содержать только буквы латинского алфавита или кириллицы, цифры, а также символы “.”, “,”, “!” ,”,”?”, “(“,”)”, “;”,”:”",
            groups = CommonValidationGroup.class)
    @UniqueWorkspaceName(message = "Рабочее пространство с таким именем уже существует",
            groups = DBValidationGroup.class)
    private String name;
    @CorrectDescription(message = "Описание должно содержать только буквы латинского алфавита или кириллицы, цифры, а также символы “.”, “,”, “!” ,”,”?”, “(“,”)”, “;”,”:”",
            groups = CommonValidationGroup.class)
    private String description;
}
