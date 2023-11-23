package team.exlab.tasks.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationException extends RuntimeException {

    private String message;

}
