package iti.alex.intake40.team9.tripreminder.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ITripDao {
    @Insert()
    public void addContact(TripModel tripModel);

    @Delete()
    public void deleteContact(TripModel tripModel);

    @Query("DELETE FROM TripModel WHERE trip_id = :trip_id")
    public void deleteByTripId(long trip_id);


    @Update()
    public void updateContact(TripModel tripModel);


    @Query("select * from TripModel")
    public List<TripModel> getTripList();

    @Query("select * from TripModel where trip_id == :trip_id  ")
    public TripModel getContact(int trip_id);


    @Query("select * from TripModel where trip_history == :trip_history  ")
    public List<TripModel> getHistory(Boolean trip_history);

    @Query("select * from TripModel where trip_history == :trip_history  ")
    public List<TripModel> getUpComming(Boolean trip_history);
}
