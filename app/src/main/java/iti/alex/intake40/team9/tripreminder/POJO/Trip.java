package iti.alex.intake40.team9.tripreminder.POJO;

import android.location.Location;

import java.util.ArrayList;

public class Trip {
    private String tripName;
    private String startPoint;
    private String endPoint;
    private ArrayList<String> notes;
    private String status;
    private String date;
    private String time;
    private String priorityImage;

    public Trip(String tripName, String startPoint, String endPoint, ArrayList<String> notes, String status,String date, String time, String priorityImage) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.notes = notes;
        this.status = status;
        this.date=date;
        this.time=time;
        this.priorityImage=priorityImage;
    }


    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }


    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        date = date;
    }

    public void setTime(String time) {
        date = time;
    }

    public void setPriorityImage(String priorityImage) {
        this.priorityImage = priorityImage;
    }

    public String getTripName() {
        return tripName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }



    public ArrayList<String> getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPriorityImage() {
        return priorityImage;
    }
}
