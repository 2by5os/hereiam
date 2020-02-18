package domain.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by ohseoklee on 2018. 11. 16..
 * table for faculty information
 */
@Entity
@Table(name = "HIA_FACULTY", schema = "db_heariam", catalog = "")
public class HiaFacultyEntity {
    private int id;
    private String name;
    private Collection<HiaStudentEntity> hiaStudentsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaFacultyEntity that = (HiaFacultyEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "hiaFacultyByFacultyId")
    public Collection<HiaStudentEntity> getHiaStudentsById() {
        return hiaStudentsById;
    }

    public void setHiaStudentsById(Collection<HiaStudentEntity> hiaStudentsById) {
        this.hiaStudentsById = hiaStudentsById;
    }
}
