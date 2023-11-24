package team.exlab.tasks.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;

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
    private Boolean isActivated = false;

    @Column(name = "date_of_expire_invite", nullable = false)
    private LocalDateTime dateOfExpireInvite;

    @ManyToOne
    @JoinColumn(name = "workspace_id", referencedColumnName = "id")
    private WorkspaceEntity workspace;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

    public void setLinkLifeTime() {
        LocalDateTime dateTimeOfInvite = LocalDateTime.now();
        this.setDateOfExpireInvite(dateTimeOfInvite.plusDays(7));
    }

    public void setInviteUniqueIdentifier() {
        this.setUniqueIdentifier(RandomStringUtils.randomAlphanumeric(40));
    }

}
