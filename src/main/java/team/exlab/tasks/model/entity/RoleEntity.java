package team.exlab.tasks.model.entity;

import jakarta.persistence.*;
import lombok.*;
import team.exlab.tasks.model.enam.UserRole;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"invites", "users"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(name = "description", length = 80, nullable = false)
    private String description;

    @OneToMany(mappedBy = "role")
    private List<InviteEntity> invites;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;
}
