package iti.alex.intake40.team9.tripreminder.POJO;

public class TripCalender {
    String Date;
    String Time;

    public TripCalender(String date, String time) {
        Date = date;
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setTime(String time) {
        Time = time;
    }
}
