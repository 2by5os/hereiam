package domain.entity;

import constant.AttendStatus;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ohseoklee on 2018. 11. 16..
 *
 */
@Entity
@Table(name = "HIA_ATTENDANCE", schema = "db_heariam", catalog = "")
public class HiaAttendanceEntity {
    private int id;
    private AttendStatus status;
    private Integer courseId;
    private Integer studentId;
    private HiaCourseEntity hiaCourseByCourseId;
    private HiaStudentEntity hiaStudentByStudentId;
    private int semesterId;
    private Timestamp attendAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    public AttendStatus getStatus() {
        return status;
    }

    public void setStatus(AttendStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "course_id", updatable = false, insertable = false)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "student_id", updatable = false, insertable = false)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaAttendanceEntity that = (HiaAttendanceEntity) o;

        if (id != that.id) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public HiaCourseEntity getHiaCourseByCourseId() {
        return hiaCourseByCourseId;
    }

    public void setHiaCourseByCourseId(HiaCourseEntity hiaCourseByCourseId) {
        this.hiaCourseByCourseId = hiaCourseByCourseId;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    public HiaStudentEntity getHiaStudentByStudentId() {
        return hiaStudentByStudentId;
    }

    public void setHiaStudentByStudentId(HiaStudentEntity hiaStudentByStudentId) {
        this.hiaStudentByStudentId = hiaStudentByStudentId;
    }

    @Basic
    @Column(name = "semester_id")
    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    @Basic
    @Column(name = "attend_at")
    public Timestamp getAttendAt() {
        return attendAt;
    }

    public void setAttendAt(Timestamp attendAt) {
        this.attendAt = attendAt;
    }

    @Override
    public String toString() {
        return "HiaAttendanceEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                ", semesterId=" + semesterId +
                ", attendAt=" + attendAt +
                '}';
    }
}
