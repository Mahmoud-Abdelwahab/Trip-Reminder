package iti.alex.intake40.team9.tripreminder.Models;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Contracts.IUpcomingFragment.UpcomingFragmentContract;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Room.AppDataBase;
import iti.alex.intake40.team9.tripreminder.Room.DbModel;

public class UpcomingFragmentModel implements UpcomingFragmentContract.IUpcomingFragmentModel {
    AppDataBase appDataBase;
    Context context;
    List<Trip> trips= new ArrayList<>();
    public UpcomingFragmentModel(Context context)
    {
        this.context=context;
    }
    @Override
    public List<Trip> getTripsFromFireBase() {
        DbModel dbModel = new DbModel(context);
       // trips=dbModel.getAllTripDb();

        return trips;
    }
}
