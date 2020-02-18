package domain.entity;

import constant.BOARD_TYPE;
import constant.USER_TYPE;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ohseoklee on 2018-12-07.
 * course board table for anyone involved in a course
 */
@Entity
@Table(name = "hia_board", schema = "db_heariam", catalog = "")
public class HiaBoardEntity {
    private int id;
    private String title;
    private String content;
    private int writer;
    private Timestamp createdAt;
    private BOARD_TYPE type;
    private USER_TYPE userType;

    @Id @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "writer")
    public int getWriter() {
        return writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    @Basic @GeneratedValue
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiaBoardEntity that = (HiaBoardEntity) o;

        if (id != that.id) return false;
        if (writer != that.writer) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + writer;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }


    @Basic
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    public BOARD_TYPE getType() {
        return type;
    }

    public void setType(BOARD_TYPE type) {
        this.type = type;
    }


    @Basic
    @Column(name = "writer_type")
    @Enumerated(value = EnumType.STRING)
    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HiaBoardEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", writer=" + writer +
                ", createdAt=" + createdAt +
                ", type=" + type +
                ", userType=" + userType +
                '}';
    }
}
