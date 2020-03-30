package iti.alex.intake40.team9.tripreminder.Room;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class DbModel implements IDbModel  {


    AppDataBase dataBase;
            ITripDao iContactDao;

public DbModel(Context con) {


        dataBase = Room.databaseBuilder(con, AppDataBase.class, "db-contacts")
        .allowMainThreadQueries()   //Allows room to do operation on main thread
        .build();

        iContactDao = dataBase.getContactDAO();
        }


        @Override
        public void addTripDb(TripModel contactModel) {
                iContactDao.addContact(contactModel);
        }

        @Override
        public List<TripModel> getAllTripDb() {
                return iContactDao.getTripList();
        }

        @Override
        public void updateTripDb(TripModel contactModel) {
                iContactDao.updateContact(contactModel);
        }

        @Override
        public TripModel getTripByID(int contact_id) {
                return iContactDao.getContact(contact_id);
        }

        @Override
        public void deleteByTripId(long trip_id) {
                iContactDao.deleteByTripId(trip_id);
        }

        @Override
        public void deleteTripDb(TripModel contactModel) {
                iContactDao.deleteContact(contactModel);
        }

        @Override
        public List<TripModel> getHistory(Boolean trip_history)
        {
               return iContactDao.getHistory(trip_history);
        }
}



