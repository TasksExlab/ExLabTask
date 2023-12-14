package team.exlab.tasks.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "uniqueIdentifier")
@Entity
@Table(name = "invites")
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "unique_identifier", length = 256, nullable = false, unique = true)
    private String uniqueIdentifier;

    @Builder.Default
    @Column(name = "is_activated", nullable = false)
    private Boolean isActivated = false;

    @Column(name = "date_of_expire_invite", nullable = false)
    private LocalDateTime dateOfExpireInvite;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "workspace_id", referencedColumnName = "id", nullable = false)
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public void setLinkLifeTime() {
        LocalDateTime dateTimeOfInvite = LocalDateTime.now();
        this.setDateOfExpireInvite(dateTimeOfInvite.plusDays(7));
    }

    public void setInviteUniqueIdentifier() {
        this.setUniqueIdentifier(RandomStringUtils.randomAlphanumeric(40));
    }

}
