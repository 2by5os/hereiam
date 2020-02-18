package domain.entity;

import javax.persistence.*;

/**
 * Created by ohseoklee on 2018-11-29.
 * course - timetable(course_time) relationship table
 */
@Entity
@Table(name = "hia_course_time_relation", schema = "db_heariam", catalog = "")
public class HiaCourseTimeRelationEntity {
    private int id;
    private int courseId;
    private HiaCourseTimeEntity hiaCourseTimeByCourseTimeId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "course_id")
    public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaCourseTimeRelationEntity that = (HiaCourseTimeRelationEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "course_time_id", referencedColumnName = "id")
    public HiaCourseTimeEntity getHiaCourseTimeByCourseTimeId() {
        return hiaCourseTimeByCourseTimeId;
    }

    public void setHiaCourseTimeByCourseTimeId(HiaCourseTimeEntity hiaCourseTimeByCourseTimeId) {
        this.hiaCourseTimeByCourseTimeId = hiaCourseTimeByCourseTimeId;
    }
}
