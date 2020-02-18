package domain.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ohseoklee on 2018-12-04.
 * table for managing semester information including date
 */
@Entity
@Table(name = "hia_semester", schema = "db_heariam", catalog = "")
public class HiaSemesterEntity {
    private int id;
    private String year;
    private String semester;
    private Timestamp beginAt;
    private Timestamp endAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "semester")
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Basic
    @Column(name = "begin_at")
    public Timestamp getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(Timestamp beginAt) {
        this.beginAt = beginAt;
    }

    @Basic
    @Column(name = "end_at")
    public Timestamp getEndAt() {
        return endAt;
    }

    public void setEndAt(Timestamp endAt) {
        this.endAt = endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaSemesterEntity that = (HiaSemesterEntity) o;

        if (id != that.id) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) return false;
        if (beginAt != null ? !beginAt.equals(that.beginAt) : that.beginAt != null) return false;
        if (endAt != null ? !endAt.equals(that.endAt) : that.endAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (beginAt != null ? beginAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        return result;
    }
}
