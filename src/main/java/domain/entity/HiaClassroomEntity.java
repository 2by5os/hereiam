package domain.entity;

import javax.persistence.*;

/**
 * Created by ohseoklee on 2018. 11. 28..
 * table for classroom information using in course table
 * table for mapping one terminal per classroom
 */
@Entity
@Table(name = "hia_classroom", schema = "db_heariam", catalog = "")
public class HiaClassroomEntity {
    private int id;
    private String name;

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

        HiaClassroomEntity that = (HiaClassroomEntity) o;

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

    private HiaTerminalEntity device;

    @OneToOne
    @JoinColumn (
            name = "id",
            referencedColumnName = "classroom_id"
    )
    public HiaTerminalEntity getDevice() {
        return device;
    }

    public void setDevice(HiaTerminalEntity device) {
        this.device = device;
    }
}
