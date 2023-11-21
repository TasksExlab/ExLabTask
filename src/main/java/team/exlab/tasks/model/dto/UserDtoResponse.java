package team.exlab.tasks.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDtoResponse {
    String name;
    String surname;
    String email;
}
