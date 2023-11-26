package team.exlab.tasks.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDtoResponse {
    private String name;
    private String surname;
    private String email;
    private String role;
}
