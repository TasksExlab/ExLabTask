package team.exlab.tasks.service.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDtoResponse {
    String name;
    String surname;
    String email;
}
