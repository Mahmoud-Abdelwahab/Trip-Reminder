package iti.alex.intake40.team9.tripreminder.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Contracts.IAddNoteFragment.AddNoteFragmentContract;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.POJO.TripConverter;
import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;

public class AddNoteFragmentModel implements AddNoteFragmentContract.IAddNoteFragmentModel {
    Context context;
    List<TripModel> tripModels = new ArrayList<>();
    List<Trip> trips= new ArrayList<>();



    public AddNoteFragmentModel(Context context){
        this.context = context;
    }

    @Override
    public List<Trip> getTripsFromRoom() {

        DbModel dbModel = new DbModel(context);
        tripModels=dbModel.getAllTripDb();
        TripConverter tripConverter = new TripConverter();
        for(int i=0; i<tripModels.size(); i++)
        {
            TripModel tripModel = tripModels.get(i);
            Trip trip = tripConverter.fromTripModelTotrip(tripModel);
            trips.add(trip);
        }
    return trips;

    }
    public void addNotestoDataBase(Trip trip){
        Trip mytrip = trip;
        TripConverter tripConverter = new TripConverter();
        DbModel dbModel = new DbModel(context);
        dbModel.updateTripDb(tripConverter.fromTripToTripModel(trip));
    }


}
