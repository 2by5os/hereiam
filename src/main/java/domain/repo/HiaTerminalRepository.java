package domain.repo;

import domain.entity.HiaTerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018-12-03.
 *
 */
@Repository
public interface HiaTerminalRepository extends JpaRepository<HiaTerminalEntity, Integer> {
    HiaTerminalEntity findOneByName(String id);
}
