package team.exlab.tasks.service.dto.workspace;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import team.exlab.tasks.service.validation.validator.annotation.UniqueWorkspaceName;

@Data
public class CreateWorkspaceDtoRequest {
    @NotEmpty(message = "Введите название рабочего пространства")
    @Size(min = 3, message = "Название рабочего пространства должно содержать хотя бы 3 символа")
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9.,!?();:]+",
            message = "Название должно содержать только буквы латинского алфавита или кириллицы, цифры, а также символы “.”, “,”, “!” ,”,”?”, “(“,”)”, “;”,”:”")
    @UniqueWorkspaceName(message = "Рабочее пространство с таким именем уже существует")
    private String name;
    //NOT required !
    @Size(min = 10, message = "Описание рабочего пространства должно содержать хотя бы 10 символов")
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9.,!?();:]+",
            message = "Описание должно содержать только буквы латинского алфавита или кириллицы, цифры, а также символы “.”, “,”, “!” ,”,”?”, “(“,”)”, “;”,”:”")
    private String description;
}
