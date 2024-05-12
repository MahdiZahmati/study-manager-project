package StudyManager.demo.Repository;

import StudyManager.demo.Model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment , Long> {
}
