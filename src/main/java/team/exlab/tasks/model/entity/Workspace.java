package team.exlab.tasks.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"users", "invites"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workspaces")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Builder.Default
    @ManyToMany(mappedBy = "workspaces", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "workspace", fetch = FetchType.LAZY)
    private List<Invite> invites = new ArrayList<>();
}
