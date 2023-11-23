package team.exlab.tasks.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class JwtResponse {
    String token;
}
