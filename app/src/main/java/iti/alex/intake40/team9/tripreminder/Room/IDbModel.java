package iti.alex.intake40.team9.tripreminder.Room;

import java.util.List;

public interface IDbModel {

    public void addTripDb(TripModel contactModel);

    public List<TripModel> getAllTripDb();

    public void updateTripDb(TripModel contactModel);

    public TripModel getTripDb(int contact_id);

    public void deleteTripDb(TripModel contactModel);

}
