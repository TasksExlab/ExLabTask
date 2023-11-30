package team.exlab.tasks.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.exlab.tasks.model.enam.UserRole;
import team.exlab.tasks.model.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRole(UserRole role);

    Optional<Role> findByRole(UserRole role);
}
