package team.exlab.tasks.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.exlab.tasks.model.entity.Workspace;

import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    boolean existsById(Long id);
    boolean existsByName(String name);

    Optional<Workspace> findById(Long id);
}
