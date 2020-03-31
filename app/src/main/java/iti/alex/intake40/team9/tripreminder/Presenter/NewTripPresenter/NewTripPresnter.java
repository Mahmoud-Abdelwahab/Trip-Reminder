package iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter;

import androidx.fragment.app.DialogFragment;

import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;
import iti.alex.intake40.team9.tripreminder.View.NewTripView.NewTrip;

public class NewTripPresnter {
    NewTrip newTrip;
//    BaseAlarm baseAlarm;
    DbModel dbModel;

    public NewTripPresnter(NewTrip trip) {
        newTrip = trip;
//        baseAlarm = new BaseAlarm(trip);
        dbModel = new DbModel(trip);
    }


    public void showDateTimePicker() {
        DialogFragment newFragment = new MyDatePicker(newTrip);
        newFragment.show(newTrip.getSupportFragmentManager(), "datePicker");
    }

    public void addNewTrip(TripModel tripe) {
        dbModel.addTripDb(tripe);
    }


}
