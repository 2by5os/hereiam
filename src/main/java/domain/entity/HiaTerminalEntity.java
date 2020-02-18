package domain.entity;

import javax.persistence.*;

/**
 * Created by ohseoklee on 2018. 11. 16..
 * table for managing terminal bluetooth information
 */
@Entity
@Table(name = "HIA_TERMINAL", schema = "db_heariam", catalog = "")
public class HiaTerminalEntity {
    private int id;
    private String name;
    private String bluetoothAddress;
    private String uuid;

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
    @Column(name = "bluetooth_address")
    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaTerminalEntity that = (HiaTerminalEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (bluetoothAddress != null ? !bluetoothAddress.equals(that.bluetoothAddress) : that.bluetoothAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bluetoothAddress != null ? bluetoothAddress.hashCode() : 0);
        return result;
    }


    @Basic
    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
