package team.exlab.tasks.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import team.exlab.tasks.model.enam.UserRole;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
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
