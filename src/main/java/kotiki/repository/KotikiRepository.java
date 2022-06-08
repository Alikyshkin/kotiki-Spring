package kotiki.repository;

import kotiki.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KotikiRepository extends JpaRepository<Kotiki, Integer> {
}
