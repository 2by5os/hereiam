package domain.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by ohseoklee on 2018. 11. 16..
 * table for managing only timetable info (not including course info)
 */
@Entity
@Table(name = "HIA_COURSE_TIME", schema = "db_heariam", catalog = "")
public class HiaCourseTimeEntity {
    private int id;
    private Integer day;
    private Integer time;
    private Collection<HiaCourseEntity> courses;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "day")
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Basic
    @Column(name = "time")
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaCourseTimeEntity that = (HiaCourseTimeEntity) o;

        if (id != that.id) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(
            name = "hia_course_time_relation",
            joinColumns = @JoinColumn(name = "course_time_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    public Collection<HiaCourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(Collection<HiaCourseEntity> hiaCourseTimeRelationsById) {
        this.courses = hiaCourseTimeRelationsById;
    }
}
