package response.course;

import response.Professor;

/**
 * Created by ohseoklee on 2018. 11. 28..
 * class info
 * it is designed for getNextClass
 */
public class CourseInfo {
    private int courseId = 0;
    private String courseName;
    private String room;
    private int day;
    private int time;
    private int endTime;
    private String deviceAddr = "";

    private Professor professor;

    public CourseInfo() {
    }

    public CourseInfo(String courseName, String room, int day, int time) {
        this.courseName = courseName;
        this.room = room;
        this.day = day;
        this.time = time;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDeviceAddr() {
        return deviceAddr;
    }

    public void setDeviceAddr(String deviceAddr) {
        this.deviceAddr = deviceAddr;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "courseName='" + courseName + '\'' +
                ", room='" + room + '\'' +
                ", day=" + day +
                ", time=" + time +
                ", deviceAddr='" + deviceAddr + '\'' +
                '}';
    }
}
