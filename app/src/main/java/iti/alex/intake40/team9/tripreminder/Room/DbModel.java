package iti.alex.intake40.team9.tripreminder.Room;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class DbModel implements IDbModel  {


    AppDataBase dataBase;
            ITripDao iTripDao;

public DbModel(Context con) {


        dataBase = Room.databaseBuilder(con, AppDataBase.class, "db-mytrips")
        .allowMainThreadQueries()   //Allows room to do operation on main thread
        .build();

        iTripDao = dataBase.getContactDAO();
        }


        @Override
        public void addTripDb(TripModel tripModel) {
                iTripDao.addContact(tripModel);
        }

        @Override
        public List<TripModel> getAllTripDb() {
                return iTripDao.getTripList();
        }

        @Override
        public void updateTripDb(TripModel tripModel) {
                iTripDao.updateContact(tripModel);
        }

        @Override
        public TripModel getTripByID(int tripModel) {
                return iTripDao.getContact(tripModel);
        }

        @Override
        public void deleteByTripId(long trip_id) {
                iTripDao.deleteByTripId(trip_id);
        }

        @Override
        public void deleteTripDb(TripModel tripModel) {
                iTripDao.deleteContact(tripModel);
        }

        @Override
        public List<TripModel> getHistory(Boolean trip_history)
        {
               return iTripDao.getHistory(trip_history);
        }


        @Override
        public List<TripModel> getUpCommingTrips(Boolean trip_history)
        {
                return iTripDao.getUpComming(trip_history);
        }



}



