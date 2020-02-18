package domain.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by ohseoklee on 2018. 11. 16..
 * table for managing course base information
 */
@Entity
@Table(name = "HIA_COURSE", schema = "db_heariam", catalog = "")
public class HiaCourseEntity {
    private int id;
    private String title;
    private Integer professorId;
    private Collection<HiaAttendanceEntity> attendance;
    private HiaProfessorEntity professor;
    private List<HiaCourseTimeEntity> courseTimes;
    private HiaClassroomEntity classroom;
    private Collection<HiaStudentEntity> students;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "professor_id", updatable = false, insertable = false)
    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaCourseEntity that = (HiaCourseEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (professorId != null ? !professorId.equals(that.professorId) : that.professorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (professorId != null ? professorId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HiaCourseEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", professorId=" + professorId +
                ", hiaAttendancesById=" + attendance +
                ", hiaProfessorByProfessorId=" + professor +
                ", hiaCourseTimesById=" + courseTimes +
                '}';
    }

    @OneToMany(mappedBy = "hiaCourseByCourseId")
    public Collection<HiaAttendanceEntity> getAttendance() {
        return attendance;
    }

    public void setAttendance(Collection<HiaAttendanceEntity> hiaAttendancesById) {
        this.attendance = hiaAttendancesById;
    }

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    public HiaProfessorEntity getProfessor() {
        return professor;
    }

    public void setProfessor(HiaProfessorEntity hiaProfessorByProfessorId) {
        this.professor = hiaProfessorByProfessorId;
    }

    @ManyToMany
    @JoinTable(
            name = "hia_course_time_relation",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "course_time_id")
    )
    @OrderBy("day asc , time asc ")
    public List<HiaCourseTimeEntity> getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(List<HiaCourseTimeEntity> hiaCourseTimesById) {
        this.courseTimes = hiaCourseTimesById;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    public HiaClassroomEntity getClassroom() {
        return classroom;
    }

    public void setClassroom(HiaClassroomEntity hiaClassroom) {
        this.classroom = hiaClassroom;
    }

    @ManyToMany (
            targetEntity = HiaStudentEntity.class
    )
    @JoinTable (
            name = "hia_take",
            joinColumns = @JoinColumn (name = "course_id"),
            inverseJoinColumns = @JoinColumn (name = "student_id")
    )
    public Collection<HiaStudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Collection<HiaStudentEntity> students) {
        this.students = students;
    }
}
