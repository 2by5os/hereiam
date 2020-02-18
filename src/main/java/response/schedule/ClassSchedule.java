package response.schedule;

/**
 * Created by ohseoklee on 2018. 11. 15..
 *
 * the response of timetable
 */

public class ClassSchedule {
    public final static int MON = 0;
    public final static int TUE = 1;
    public final static int WED = 2;
    public final static int THU = 3;
    public final static int FRI = 4;
    public final static int SAT = 5;
    public final static int SUN = 6;

    public String[][] schedule = new String[7][24];

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(int day, int time, String value) {
        schedule[day][time] = value;
    }

    public static int convertToDay(String strDay) {
        int result = -1;

        switch (strDay) {
            case "MON" :
                result = MON;
                break;
            case "TUE" :
                result = TUE;
                break;
            case "WED" :
                result = WED;
                break;
            case "THU" :
                result = THU;
                break;
            case "FRI" :
                result = FRI;
                break;
            case "SAT" :
                result = SAT;
                break;
            case "SUN" :
                result = SUN;
                break;
        }

        return result;
    }

    public static String convertToStr(int intDay) {
        String result = null;

        switch (intDay) {
            case MON :
                result = "MON";
                break;
            case TUE :
                result = "TUE";
                break;
            case WED :
                result = "WEB";
                break;
            case THU :
                result = "THU";
                break;
            case FRI :
                result = "FRI";
                break;
            case SAT :
                result = "SAT";
                break;
            case SUN :
                result = "SUN";
                break;
        }

        return result;
    }
}
