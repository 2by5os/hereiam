package domain.repo;

import domain.entity.HiaAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018. 11. 11..
 *
 */
@Repository
public interface HiaAuthenticationRepository extends JpaRepository<HiaAuthenticationEntity, Integer> {
    HiaAuthenticationEntity findOneByToken(String token);
    HiaAuthenticationEntity findOneByUsername(String username);
}
