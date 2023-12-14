package team.exlab.tasks.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import team.exlab.tasks.service.interfaces.IWorkspaceValidationService;
import team.exlab.tasks.service.validation.validator.annotation.UniqueWorkspaceName;

public class UniqueWorkspaceNameValidator
        implements ConstraintValidator<UniqueWorkspaceName, String> {
    @Autowired
    private IWorkspaceValidationService workspaceValidationService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return workspaceValidationService.isValidName(name);
    }
}
