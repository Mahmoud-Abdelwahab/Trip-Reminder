package iti.alex.intake40.team9.tripreminder.Contracts.IUpcomingFragment;

import android.text.Layout;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.POJO.Trip;

public interface UpcomingFragmentContract {
    public interface IUpcomingFragmentView{
        public void onDataReceived(List<Trip> trips);
    }
    public interface IUpcomingFragmentPresenter{
       public void getTrips();
    }
    public interface IUpcomingFragmentModel{
        public List<Trip> getTripsFromRoom();


    }

}
