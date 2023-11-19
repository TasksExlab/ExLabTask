package team.exlab.tasks.model.dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String passwordConfirm;

    public boolean getIsAgreeUserToProcOfPersData() {
        return true;
    }
}
