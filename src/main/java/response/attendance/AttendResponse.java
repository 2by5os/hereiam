package response.attendance;

import constant.AttendStatus;

/**
 * Created by ohseoklee on 2018-12-04.
 * response for attend result
 */
public class AttendResponse {
    private AttendStatus attendStatus = AttendStatus.FAIL;

    public AttendStatus getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(AttendStatus attendStatus) {
        this.attendStatus = attendStatus;
    }
}
