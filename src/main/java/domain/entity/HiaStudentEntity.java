package domain.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by ohseoklee on 2018. 11. 16..
 * table for managing student information
 */
@Entity
@Table(name = "HIA_STUDENT", schema = "db_heariam")
public class HiaStudentEntity {
    private int id;
    private String name;
    private Integer facultyId;
    private String studentNum;
    private String password;
    private Collection<HiaAttendanceEntity> hiaAttendancesById;
    private HiaFacultyEntity hiaFacultyByFacultyId;
    private List<HiaCourseEntity> takedCourses;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "faculty_id", updatable = false, insertable = false)
    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    @Basic
    @Column(name = "student_num")
    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaStudentEntity that = (HiaStudentEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (facultyId != null ? !facultyId.equals(that.facultyId) : that.facultyId != null) return false;
        if (studentNum != null ? !studentNum.equals(that.studentNum) : that.studentNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (facultyId != null ? facultyId.hashCode() : 0);
        result = 31 * result + (studentNum != null ? studentNum.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "hiaStudentByStudentId")
    public Collection<HiaAttendanceEntity> getHiaAttendancesById() {
        return hiaAttendancesById;
    }

    public void setHiaAttendancesById(Collection<HiaAttendanceEntity> hiaAttendancesById) {
        this.hiaAttendancesById = hiaAttendancesById;
    }

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    public HiaFacultyEntity getHiaFacultyByFacultyId() {
        return hiaFacultyByFacultyId;
    }

    public void setHiaFacultyByFacultyId(HiaFacultyEntity hiaFacultyByFacultyId) {
        this.hiaFacultyByFacultyId = hiaFacultyByFacultyId;
    }

    @ManyToMany
    @JoinTable(
        name = "hia_take",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    public List<HiaCourseEntity> getTakedCourses() {
        return takedCourses;
    }

    public void setTakedCourses(List<HiaCourseEntity> takedCourses) {
        this.takedCourses = takedCourses;
    }

    @Override
    public String toString() {
        return "HiaStudentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", facultyId=" + facultyId +
                ", studentNum='" + studentNum + '\'' +
                ", hiaAttendancesById=" + hiaAttendancesById +
                ", hiaFacultyByFacultyId=" + hiaFacultyByFacultyId +
                ", takedCourses=" + takedCourses +
                '}';
    }
}
