package domain.repo;

import domain.entity.HiaAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018. 11. 11..
 *
 */
@Repository
public interface HiaAdminEntityRepository extends JpaRepository<HiaAdminEntity, Integer> {
    HiaAdminEntity findOneByUsername(String username);
}
