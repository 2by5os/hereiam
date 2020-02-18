package domain.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by ohseoklee on 2018. 11. 16..
 * table for managing professor information
 */
@Entity
@Table(name = "HIA_PROFESSOR", schema = "db_heariam", catalog = "")
public class HiaProfessorEntity {
    private int id;
    private String name;
    private Collection<HiaCourseEntity> hiaCoursesById;
    private String username;
    private String password;


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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

        HiaProfessorEntity that = (HiaProfessorEntity) o;

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

    @OneToMany(mappedBy = "professor")
    public Collection<HiaCourseEntity> getHiaCoursesById() {
        return hiaCoursesById;
    }

    public void setHiaCoursesById(Collection<HiaCourseEntity> hiaCoursesById) {
        this.hiaCoursesById = hiaCoursesById;
    }
}
