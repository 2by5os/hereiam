package domain.repo;

import domain.entity.HiaCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ohseoklee on 2018. 11. 28..
 * course
 */
@Repository
public interface HiaCourseRepository extends JpaRepository<HiaCourseEntity, Integer> {
    List<HiaCourseEntity> findByStudents_StudentNum(String studentNum);
    HiaCourseEntity findByStudents_StudentNumAndCourseTimes_DayAndCourseTimes_Time(String studentNum, int day, int time);
    Long countByStudents_StudentNumAndCourseTimes_Day(String studentNum, int day);
}
