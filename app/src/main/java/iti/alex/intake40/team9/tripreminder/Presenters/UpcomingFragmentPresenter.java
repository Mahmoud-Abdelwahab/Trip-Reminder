package iti.alex.intake40.team9.tripreminder.Presenters;

import android.content.Context;

import java.util.ArrayList;

import iti.alex.intake40.team9.tripreminder.Contracts.IUpcomingFragment.UpcomingFragmentContract;
import iti.alex.intake40.team9.tripreminder.Models.UpcomingFragmentModel;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Views.UpcomingFragmentView;

public class UpcomingFragmentPresenter implements UpcomingFragmentContract.IUpcomingFragmentPresenter {
    UpcomingFragmentContract.IUpcomingFragmentModel iUpcomingFragmentModel;
    UpcomingFragmentContract.IUpcomingFragmentView iUpcomingFragmentView;
    ArrayList<Trip> trips=new ArrayList<Trip>();
    Context context;

    public UpcomingFragmentPresenter(UpcomingFragmentView upcomingFragmentView , Context context) {
        this.context=context;
        iUpcomingFragmentModel=new UpcomingFragmentModel(this.context);
        iUpcomingFragmentView=upcomingFragmentView;

    }

    @Override
    public void getTrips() {
        trips =iUpcomingFragmentModel.getTripsFromFireBase();
        iUpcomingFragmentView.onDataReceived(trips);
    }
}
