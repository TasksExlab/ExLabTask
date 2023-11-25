package team.exlab.tasks.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workspaces")
public class WorkspaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workspace_name", length = 50, nullable = false)
    private String workspaceName;

    @Column(name = "desc_workspace", length = 500)
    private String descriptionWorkspace;

    @Builder.Default
    @ManyToMany(mappedBy = "workspaces")
    private List<UserEntity> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "workspace")
    private List<InviteEntity> invites = new ArrayList<>();
}
