package kotiki.repository;

import kotiki.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {
}