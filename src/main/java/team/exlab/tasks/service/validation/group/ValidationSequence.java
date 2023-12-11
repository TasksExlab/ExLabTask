package team.exlab.tasks.service.validation.group;

import jakarta.validation.GroupSequence;

@GroupSequence({CommonValidationGroup.class, DBValidationGroup.class})
public interface ValidationSequence {
}
