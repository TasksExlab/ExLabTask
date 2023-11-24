package team.exlab.tasks.service.dto.user;

import lombok.Data;

@Data
public class UserDtoResponse {
    private String name;
    private String surname;
    private String email;
    private String role;
}
