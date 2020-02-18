package domain.repo;

import domain.entity.HiaSemesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018-12-05.
 *
 */
@Repository
public interface SemesterRepository extends JpaRepository<HiaSemesterEntity, Integer> {
}
