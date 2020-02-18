package response.attendance;

import constant.AttendStatus;
import domain.entity.HiaAttendanceEntity;

import java.sql.Timestamp;

/**
 * Created by ohseoklee on 2018-12-05.
 *
 */
public class AttendInfo {
    private int id;
    private AttendStatus status;
    private Timestamp attendAt;
    private int week;

    public AttendInfo() {
    }

    public AttendInfo(HiaAttendanceEntity attend, int week) {
        this.id = attend.getId();
        this.status = attend.getStatus();
        this.attendAt = attend.getAttendAt();
        this.week = week;
    }

    public AttendInfo(AttendStatus status, long attendAt, int week) {
        this.id = 0;
        this.status = status;
        this.attendAt = new Timestamp(attendAt);
        this.week = week;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AttendStatus getStatus() {
        return status;
    }

    public void setStatus(AttendStatus status) {
        this.status = status;
    }

    public Timestamp getAttendAt() {
        return attendAt;
    }

    public void setAttendAt(Timestamp attendAt) {
        this.attendAt = attendAt;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
