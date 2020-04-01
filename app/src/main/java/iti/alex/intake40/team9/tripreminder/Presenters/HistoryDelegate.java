package iti.alex.intake40.team9.tripreminder.Presenters;

import java.util.List;

import iti.alex.intake40.team9.tripreminder.POJO.Trip;

public interface HistoryDelegate {
    public void showImage();
    public void fillRecycler(List<Trip> list);
}
