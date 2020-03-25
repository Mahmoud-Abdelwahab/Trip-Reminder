package iti.alex.intake40.team9.tripreminder.POJO;

import android.location.Location;

import java.util.ArrayList;

public class Trip {
    private String tripName;
    private TripLocation startPoint;
    private TripLocation endPoint;
    private TripCalender calender;
    private ArrayList<TripNote> notes;
    private String status;

    public Trip(String tripName, TripLocation startPoint, TripLocation endPoint,TripCalender calender, ArrayList<TripNote> notes, String status) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.calender=calender;
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



    public ArrayList<TripNote> getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }
}
