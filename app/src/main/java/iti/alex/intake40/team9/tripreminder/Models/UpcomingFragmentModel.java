package iti.alex.intake40.team9.tripreminder.Models;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Contracts.IUpcomingFragment.UpcomingFragmentContract;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.BaseAlarm;
import iti.alex.intake40.team9.tripreminder.Room.AppDataBase;
import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;

public class UpcomingFragmentModel implements UpcomingFragmentContract.IUpcomingFragmentModel {
    AppDataBase appDataBase;
    Context context;
    List<Trip> trips= new ArrayList<>();
    List<TripModel> tripModels = new ArrayList<>();
    public UpcomingFragmentModel(Context context)
    {
        this.context=context;
    }
    @Override
    public List<Trip> getTripsFromFireBase() {
        DbModel dbModel = new DbModel(context);
        tripModels=dbModel.getAllTripDb();
        for(int i=0 ; i<tripModels.size();i++)
        {
            Trip trip=new Trip();
            trips.add(trip);
            trips.get(i).setTripName(tripModels.get(i).getTitle());
            trips.get(i).setStartPoint(tripModels.get(i).getStartPoint());
            trips.get(i).setEndPoint(tripModels.get(i).getEndPoint());
            String dateTime=getTimeAndDate(tripModels.get(i).getDateTime());
            trips.get(i).setTime(dateTime.substring(13,18));
            trips.get(i).setDate(dateTime.substring(0,10));

            trips.get(i).setNotes(tripModels.get(i).getNotes());
            trips.get(i).setPriorityImage(tripModels.get(i).getImportance());
        }

        return trips;
    }
    String getTimeAndDate(Long dateTime ){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   hh:mm:ss.SSS");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTime);
        return formatter.format(calendar.getTime());
    }
}
