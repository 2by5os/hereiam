package response;

import constant.BOARD_TYPE;
import constant.USER_TYPE;
import domain.entity.HiaBoardEntity;

import java.sql.Timestamp;

/**
 * Created by ohseoklee on 2018-12-07.
 */
public class BoardResponse {
    private int id;
    private String title;
    private String content;
    private int writer;
    private Timestamp createdAt;
    private BOARD_TYPE type;
    private USER_TYPE userType;
    private String writerName;

    public BoardResponse(HiaBoardEntity hiaBoardEntity) {
        this.id = hiaBoardEntity.getId();
        this.content = hiaBoardEntity.getContent();
        this.writer = hiaBoardEntity.getWriter();
        this.createdAt = hiaBoardEntity.getCreatedAt();
        this.type = hiaBoardEntity.getType();
        this.userType = hiaBoardEntity.getUserType();
        this.title = hiaBoardEntity.getTitle();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWriter() {
        return writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public BOARD_TYPE getType() {
        return type;
    }

    public void setType(BOARD_TYPE type) {
        this.type = type;
    }

    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
}
