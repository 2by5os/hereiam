import domain.entity.HiaStudentEntity;
import domain.repo.HiaAttendanceRepository;
import domain.repo.HiaCourseRepository;
import domain.repo.HiaStudentEntityRepository;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import service.StudentService;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml", "classpath:db-config.xml"})
@WebAppConfiguration
public class RepositoryTest {

    @Resource
    private HiaStudentEntityRepository studentEntityRepository;

    @Resource
    private HiaCourseRepository hiaCourseRepository;

    @Resource
    private StudentService studentService;

    @Resource
    private HiaAttendanceRepository hiaAttendanceRepository;

    @Test
    @Transactional
    public void test() {
        HiaStudentEntity studentEntity = studentEntityRepository.findOne(1);

        Hibernate.initialize(studentEntity.getTakedCourses());

        System.out.println(studentEntity);
    }

    @Test
    public void testService() {
        HiaStudentEntity studentEntity = studentEntityRepository.findOne(1);

        Hibernate.initialize(studentEntity.getTakedCourses());

        System.out.println(studentEntity);
    }

    @Test
    public void get() {
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int period = calendar.get(Calendar.HOUR_OF_DAY) - 9;

        System.out.println(day + " : " + period);
    }

    @Test
    public void getNextCourse() {
        studentService.getNextCourse("21360017");
    }

    @Test
    public void testFindAttend() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.set(Calendar.DAY_OF_MONTH, tomorrow.get(Calendar.DAY_OF_MONTH) + 1);

        System.out.println(
                hiaAttendanceRepository.findStudentAttendCourseIdAndDateBetween(
                        1,
                        2,
                        new Date(today.getTimeInMillis()),
                        new Date(tomorrow.getTimeInMillis())
                )
        );
    }

    @Test
    public void timeTest() {
        Calendar start = Calendar.getInstance();
        start.set(2016, 8, 28);
        Calendar end = Calendar.getInstance();
        end.set(2016, 12, 19);

        while (compare(start, end)) {
            System.out.println(start.getTime() + " : " + end.getTime());

            start.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH) + 7);
        }
    }

    @Test
    public void testYear() {
        System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
    }

    public boolean compare(Calendar a, Calendar b) {
        return a.getTimeInMillis() < b.getTimeInMillis();
    }
}
