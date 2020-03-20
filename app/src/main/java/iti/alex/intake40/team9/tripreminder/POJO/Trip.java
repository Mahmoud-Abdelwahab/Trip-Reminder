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

}
