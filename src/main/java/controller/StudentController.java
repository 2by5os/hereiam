package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import response.Student;
import response.attendance.AttendHistory;
import response.course.CourseInfo;
import response.course.NextCourseInfo;
import response.schedule.ClassSchedule;
import security.AuthRole;
import security.PreAuth;
import service.StudentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ohseoklee on 2018. 11. 11..
 *
 */

@RestController
@RequestMapping("/students")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Student> getStudentList() {
        return studentService.getStudents();
    }

    @PreAuth(role = AuthRole.ROLE_STUDENT)
    @RequestMapping(value = "/{studentNum}/timeTable", method = RequestMethod.GET)
    public ClassSchedule getStudentTimeTable(@PathVariable String studentNum) {
    	System.out.println("컨트롤러 진입");
        return studentService.getSchedule(studentNum);
    }

    @PreAuth(role = AuthRole.ROLE_STUDENT)
    @RequestMapping(value = "/{studentNum}/getNextClassInfo", method = RequestMethod.GET)
    public NextCourseInfo getNextClassInfo(@PathVariable String studentNum) {
        return studentService.getNextCourse(studentNum);
    }

    @RequestMapping(value = "/{studentNum}/attendHistory/{courseId}/{semester}")
    public AttendHistory getAttendInfo(@PathVariable String studentNum, @PathVariable Integer courseId, @PathVariable Integer semester) {
        return studentService.getAttendHistory(studentNum, courseId, semester);
    }

    @RequestMapping(value = "/{studentNum}/takes")
    public List<CourseInfo> getTakedCourse(@PathVariable String studentNum) {
        return studentService.getTakedCourses(studentNum);
    }
}
