package iti.alex.intake40.team9.tripreminder.Models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.room.Room;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Contracts.IUpcomingFragment.UpcomingFragmentContract;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.POJO.TripConverter;
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
    public List<Trip> getTripsFromRoom() {
        DbModel dbModel = new DbModel(context);
        tripModels=dbModel.getAllTripDb();
        for(int i=0 ; i<tripModels.size();i++)
        {
            Trip trip=new Trip();
            TripConverter tripConverter = new TripConverter();
            trips.add(tripConverter.fromTripModelTotrip(tripModels.get(i)));
        }

        return trips;
    }

//    @Override
//    public List<Trip> getTripsFromFireBase() {
//        trips=FireBaseModel.sharedInstance.getTrips();
//        return trips;
//    }

//    @Override
//    public List<Trip> checkFromWhereToGetData() {
//        Log.i("mytext", ""+isInternetAvailable());
//        if(IsConnectionAvailable())
//            {
//                trips = getTripsFromFireBase();
//
//
//            }
//        else {
//          trips= getTripsFromRoom();
//        }
//        List<Trip> tripsFiltered= new ArrayList<>();
//        for(int i =0; i<trips.size(); i++)
//        {
//            if(!trips.get(i).isHistory()) {
//                tripsFiltered.add(trips.get(i));
//            }
//
//        }
//        return tripsFiltered;
//
//    }

    String getTimeAndDate(Long dateTime ){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   hh:mm:ss.SSS");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTime);
        return formatter.format(calendar.getTime());
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public boolean IsConnectionAvailable(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }
}
