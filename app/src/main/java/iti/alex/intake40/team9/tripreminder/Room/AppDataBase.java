package iti.alex.intake40.team9.tripreminder.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {TripModel.class},version = 1)
@TypeConverters({Converter.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract ITripDao getContactDAO();
}
