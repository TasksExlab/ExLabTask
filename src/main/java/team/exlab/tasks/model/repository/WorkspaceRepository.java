package team.exlab.tasks.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.exlab.tasks.model.entity.WorkspaceEntity;

import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long> {

    boolean existsById(Long id);

    Optional<WorkspaceEntity> findById(Long id);
}