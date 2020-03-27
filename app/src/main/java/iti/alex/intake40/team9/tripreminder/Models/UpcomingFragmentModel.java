package iti.alex.intake40.team9.tripreminder.Models;

import java.util.ArrayList;

import iti.alex.intake40.team9.tripreminder.Contracts.IUpcomingFragment.UpcomingFragmentContract;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;

public class UpcomingFragmentModel implements UpcomingFragmentContract.IUpcomingFragmentModel {
    @Override
    public ArrayList<Trip> getTripsFromFireBase() {
        ArrayList<Trip> trips=new ArrayList<Trip>();
        ArrayList<String>notes=new ArrayList<>();
        notes.add("Do not forget to do that");
        notes.add("Do you remember that");
        Trip trip= new Trip("ITI","KafrEldawar","Alexandria",notes,"Upcoming","12/11/2020","20:25","ay 7aga");
        trips.add(trip);
        trips.add(trip);
        trips.add(trip);
        trips.add(trip);
        trips.add(trip);
        trips.add(trip);
        trips.add(trip);

        return trips;
    }
}
