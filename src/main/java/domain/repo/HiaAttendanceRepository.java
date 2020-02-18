package domain.repo;

import domain.entity.HiaAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ohseoklee on 2018-12-03.
 *
 */
@Repository
public interface HiaAttendanceRepository extends JpaRepository<HiaAttendanceEntity, Integer> {
    HiaAttendanceEntity findOneByStudentId(int sId);

    @Query(value = "select attend from HiaAttendanceEntity attend where attend.studentId = ?1 and attend.courseId = ?2 and attend.attendAt between ?3 and ?4")
    HiaAttendanceEntity findStudentAttendCourseIdAndDateBetween(int studentId, int courseId, Date startAt, Date endAt);

    @Query(value = "select attend from HiaAttendanceEntity attend where attend.studentId = ?1 and attend.courseId = ?2 and attend.attendAt between ?3 and ?4")
    List<HiaAttendanceEntity> findAllByStudentAttendCourseIdAndDateBetween(int studentId, int courseId, Timestamp startAt, Timestamp endAt);
}
