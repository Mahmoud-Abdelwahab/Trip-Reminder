package iti.alex.intake40.team9.tripreminder.POJO;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    private int id;
    private String tripName;
    private String startPoint;



    private String endPoint;
    private List<String> notes;


    private String date;
    private String time;
    private String priorityImage;
    private String repetition;
    private String rounded;
    private boolean history;
    private long dateAndTime;

    public Trip(String tripName, String startPoint, String endPoint, List<String> notes,String date, String time, String priorityImage) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.notes = notes;
        this.date=date;
        this.time=time;
        this.priorityImage=priorityImage;
    }

    public Trip(){}


    public void setHistory(boolean history) {
        this.history = history;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateAndTime(long dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public void setRounded(String rounded) {
        this.rounded = rounded;
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


    public void setNotes(List<String> notes) {
        this.notes = notes;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getId() {
        return id;
    }

    public String getRepetition() {
        return repetition;
    }

    public boolean isHistory() {
        return history;
    }

    public String getRounded() {
        return rounded;
    }

    public List<String> getNotes() {
        return notes;
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

    public long getDateAndTime() {
        return dateAndTime;
    }
}
