package team.exlab.tasks.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WorkspaceException extends RuntimeException {

    private String message;
}
