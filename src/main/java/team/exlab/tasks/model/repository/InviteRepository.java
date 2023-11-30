package team.exlab.tasks.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.exlab.tasks.model.entity.Invite;

import java.util.Optional;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

    Optional<Invite> getInviteEntityByUniqueIdentifier(String uniqueIdentifier);

    Optional<Invite> getInviteEntityByEmailAndUniqueIdentifier(String email, String uniqueIdentifier);

    boolean existsByEmail(String email);
}
