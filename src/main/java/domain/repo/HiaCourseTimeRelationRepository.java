package domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.entity.HiaCourseTimeRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018. 11. 28..
 * course
 */
@Repository
public interface HiaCourseTimeRelationRepository extends JpaRepository<HiaCourseTimeRelationEntity, Integer> {
	public int countByCourseId(int id);
}
