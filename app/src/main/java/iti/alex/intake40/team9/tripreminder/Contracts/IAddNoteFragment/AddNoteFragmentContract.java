package iti.alex.intake40.team9.tripreminder.Contracts.IAddNoteFragment;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.POJO.Trip;

public interface AddNoteFragmentContract {
    public interface IAddNoteFragmentView{
        public void onDataReceived(List<Trip> trips);
    }
    public interface IAddNoteFragmentPresenter{

        public void getTrips();
    }
    public interface IAddNoteFragmentModel{
        public List<Trip> getTripsFromRoom();
//        public List<Trip> getTripsFromRoom();
//        public void addNottoFireBase(Trip trip);
        public void addNotestoDataBase(Trip trip);

    }
}
