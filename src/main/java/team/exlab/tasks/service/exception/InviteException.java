package team.exlab.tasks.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InviteException extends RuntimeException {

    private String message;
}
