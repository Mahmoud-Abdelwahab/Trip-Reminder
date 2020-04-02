package iti.alex.intake40.team9.tripreminder.Presenters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Models.FireBaseModel;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.POJO.TripConverter;
import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;

public class HistoryPresenter {

    private Fragment fragment;
    private HistoryDelegate delegate;

    public HistoryPresenter(Fragment fragment, HistoryDelegate delegate) {
        this.fragment = fragment;
        this.delegate = delegate;
        getList();
    }

    private void getList() {
        List<Trip> list = new ArrayList<>();

        List<TripModel> dbModel = new DbModel(fragment.getContext()).getHistory(true);
        TripConverter tc = new TripConverter();
        for (int i = 0; i < dbModel.size(); i++)
            list.add(tc.fromTripModelTotrip(dbModel.get(i)));
        if (list.isEmpty()) {
            delegate.showImage();
        }
        else {
            delegate.fillRecycler(list);
        }
    }
}
