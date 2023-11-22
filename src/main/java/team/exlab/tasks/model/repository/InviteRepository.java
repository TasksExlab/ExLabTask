package team.exlab.tasks.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.exlab.tasks.model.entity.InviteEntity;

import java.util.Optional;

@Repository
public interface InviteRepository extends JpaRepository<InviteEntity, Long> {

    Optional<InviteEntity> getInviteEntityByUniqueIdentifier(String uniqueIdentifier);

    Optional<InviteEntity> getInviteEntityByEmailAndUniqueIdentifier(String email, String uniqueIdentifier);

    boolean existsByEmail(String email);
}
