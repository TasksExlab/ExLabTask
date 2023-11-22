package team.exlab.tasks.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invites")
public class InviteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @Column(name = "unique_identifier", length = 256, nullable = false)
    private String uniqueIdentifier;

    @Builder.Default
    @Column(name = "is_activated", nullable = false)
    private Boolean activated = true;

    @Column(name = "date_of_expire_invite", nullable = false)
    private LocalDateTime dateOfExpireInvite;

    @ManyToOne
    @JoinColumn(name = "workspace_id", referencedColumnName = "id")
    private WorkspaceEntity workspace;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

}
