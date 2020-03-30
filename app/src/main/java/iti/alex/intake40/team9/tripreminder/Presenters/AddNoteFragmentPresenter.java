package iti.alex.intake40.team9.tripreminder.Presenters;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Contracts.IAddNoteFragment.AddNoteFragmentContract;
import iti.alex.intake40.team9.tripreminder.Models.AddNoteFragmentModel;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Views.AddNoteFragmentView;

public class AddNoteFragmentPresenter implements AddNoteFragmentContract.IAddNoteFragmentPresenter {
    AddNoteFragmentContract.IAddNoteFragmentModel iAddNoteFragmentModel;
    AddNoteFragmentContract.IAddNoteFragmentView iAddNoteFragmentView;
    List<Trip> trips=new ArrayList<Trip>();


    public AddNoteFragmentPresenter(AddNoteFragmentView addNoteFragmentView) {
        this.iAddNoteFragmentView=addNoteFragmentView;
        iAddNoteFragmentModel=new AddNoteFragmentModel();
    }

    @Override
    public void getTrips() {
        trips=iAddNoteFragmentModel.getTripsFromFireBase();
        iAddNoteFragmentView.onDataReceived(trips);
    }
}
