package response.course;

import domain.entity.HiaCourseEntity;

/**
 * Created by ohseoklee on 2018-11-29.
 *
 */
public class NextCourseInfo {
    private CourseInfo courseInfo = null;
    private STATUS status = STATUS.NONE;

    public enum STATUS {
        NONE("NONE"), CUR("CUR"), NEXT("NEXT");

        STATUS(String status) {
        }
    }

    public NextCourseInfo() {

    }

    public STATUS getStatus() {
        return status;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public void init(HiaCourseEntity hiaCourseEntity, STATUS status) {
        if (hiaCourseEntity == null) return;

        this.courseInfo = new CourseInfo();
        this.status = status;
    }
}
