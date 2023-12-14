package team.exlab.tasks.model.entity;

import jakarta.persistence.*;
import lombok.*;
import team.exlab.tasks.model.enam.UserRole;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "role")
@ToString(exclude = {"invites", "users"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", length = 20, nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(name = "description", length = 80, nullable = false, unique = true)
    private String description;
    @Builder.Default
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Invite> invites = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
