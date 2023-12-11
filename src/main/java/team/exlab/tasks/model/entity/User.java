package team.exlab.tasks.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "email")
@ToString(exclude = "workspaces")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "surname", length = 30, nullable = false)
    private String surname;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_workspace",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workspace_id"))
    private List<Workspace> workspaces = new ArrayList<>();

    public void addWorkspace(Workspace workspace) {
        this.getWorkspaces().add(workspace);
        workspace.getUsers().add(this);
    }
}
