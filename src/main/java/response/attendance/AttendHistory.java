package response.attendance;

import response.Semester;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohseoklee on 2018-12-05.
 */
public class AttendHistory {
    private List<AttendInfo> attendInfos = new ArrayList<>();
    private Semester semester;

    public List<AttendInfo> getAttendInfos() {
        return attendInfos;
    }

    public void setAttendInfos(List<AttendInfo> attendInfos) {
        this.attendInfos = attendInfos;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
