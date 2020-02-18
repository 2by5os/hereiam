package domain.repo;

import domain.entity.HiaStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018. 11. 11..
 *
 */
@Repository
public interface HiaStudentEntityRepository extends JpaRepository<HiaStudentEntity, Integer> {
    HiaStudentEntity findOneByStudentNum(String studentNum);
}
