package team.exlab.tasks.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InviteException extends RuntimeException {

    private String massege;
}
