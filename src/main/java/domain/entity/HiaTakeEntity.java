package domain.entity;

import javax.persistence.*;

/**
 * Created by ohseoklee on 2018. 11. 16..
 * student - course relationship table
 */
@Entity
@Table(name = "hia_take", schema = "db_heariam", catalog = "")
public class HiaTakeEntity {
    private int id;
    private Integer courseId;
    private Integer studentId;
    private HiaCourseEntity hiaCourseByCourseId;
    private HiaStudentEntity hiaStudentByStudentId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        HiaTakeEntity that = (HiaTakeEntity) o;

        if (id != that.id) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
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
}
