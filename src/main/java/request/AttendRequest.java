package request;

import response.course.CourseInfo;

import java.util.Date;

/**
 * Created by ohseoklee on 2018-12-04.
 *
 */
public class AttendRequest {
    private String studentNum;
    private Date attendAt;
    private CourseInfo courseInfo;

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public Date getAttendAt() {
        return attendAt;
    }

    public void setAttendAt(Date attendAt) {
        this.attendAt = attendAt;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    @Override
    public String toString() {
        return "AttendRequest{" +
                "studentNum='" + studentNum + '\'' +
                ", attendAt=" + attendAt +
                ", courseInfo=" + courseInfo +
                '}';
    }
}
