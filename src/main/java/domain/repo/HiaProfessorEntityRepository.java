package domain.repo;

import domain.entity.HiaProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018. 11. 11..
 *
 */
@Repository
public interface HiaProfessorEntityRepository extends JpaRepository<HiaProfessorEntity, Integer> {
    HiaProfessorEntity findOneByUsername(String username);
}
