package iti.alex.intake40.team9.tripreminder.POJO;

import android.location.Location;

import java.util.ArrayList;

public class Trip {
    private String tripName;
    private TripLocation startPoint;
    private TripLocation endPoint;
    private TripTime triptime;
    private TripDate tripdate;
    private ArrayList<TripNote> notes;
    private String status;

    public Trip(String tripName, TripLocation startPoint, TripLocation endPoint, TripTime triptime, TripDate tripdate, ArrayList<TripNote> notes, String status) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.triptime = triptime;
        this.tripdate = tripdate;
        this.notes = notes;
        this.status = status;
    }
    public Trip (String tripName,String tripstatus)
    {
        this.tripName=tripName;
        this.status=tripstatus;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setStartPoint(TripLocation startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(TripLocation endPoint) {
        this.endPoint = endPoint;
    }

    public void setTriptime(TripTime triptime) {
        this.triptime = triptime;
    }

    public void setTripdate(TripDate tripdate) {
        this.tripdate = tripdate;
    }

    public void setNotes(ArrayList<TripNote> notes) {
        this.notes = notes;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTripName() {
        return tripName;
    }

    public TripLocation getStartPoint() {
        return startPoint;
    }

    public TripLocation getEndPoint() {
        return endPoint;
    }

    public TripTime getTriptime() {
        return triptime;
    }

    public TripDate getTripdate() {
        return tripdate;
    }

    public ArrayList<TripNote> getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }
}
