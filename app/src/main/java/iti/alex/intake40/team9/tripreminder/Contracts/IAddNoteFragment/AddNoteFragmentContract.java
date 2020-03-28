package iti.alex.intake40.team9.tripreminder.Contracts.IAddNoteFragment;

import java.util.ArrayList;

import iti.alex.intake40.team9.tripreminder.POJO.Trip;

public interface AddNoteFragmentContract {
    public interface IAddNoteFragmentView{
        public void onDataReceived(ArrayList<Trip> trips);
    }
    public interface IAddNoteFragmentPresenter{
        public void getTrips();
    }
    public interface IAddNoteFragmentModel{
        public ArrayList<Trip> getTripsFromFireBase();
    }
}
