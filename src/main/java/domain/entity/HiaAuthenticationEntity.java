package domain.entity;

import org.hibernate.annotations.Where;
import security.AuthRole;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ohseoklee on 2018-11-18.
 *
 */
@Entity
@Table(name = "HIA_AUTHENTICATION", schema = "db_heariam", catalog = "")
@Where(clause = "expired_at >= NOW()")
public class HiaAuthenticationEntity {
    private int id;
    private String token;
    private Timestamp createdAt;
    private Timestamp expiredAt;
    private AuthRole role;
    private String username;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "expired_at")
    public Timestamp getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Timestamp expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Basic
    @Column(name = "role")
    public AuthRole getRole() {
        return role;
    }

    public void setRole(AuthRole role) {
        this.role = role;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaAuthenticationEntity that = (HiaAuthenticationEntity) o;

        if (id != that.id) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (expiredAt != null ? !expiredAt.equals(that.expiredAt) : that.expiredAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (expiredAt != null ? expiredAt.hashCode() : 0);
        return result;
    }
}
