package team.exlab.tasks.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDetailsId {
    private String workspace;
    private UserEntity user;
    private RoleEntity role;
}
