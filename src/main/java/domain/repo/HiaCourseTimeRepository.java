package domain.repo;

import domain.entity.HiaCourseTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ohseoklee on 2018. 11. 28..
 * course
 */
@Repository
public interface HiaCourseTimeRepository extends JpaRepository<HiaCourseTimeEntity, Integer> {
    public HiaCourseTimeEntity findOneByDayAndTime(int day, int time);
}
