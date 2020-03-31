package iti.alex.intake40.team9.tripreminder.POJO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import iti.alex.intake40.team9.tripreminder.Room.TripModel;

public class TripConverter {
    public Trip fromTripModelTotrip(TripModel tripModel)
    {
        Trip trip= new Trip();
        trip.setId(tripModel.getId());
        trip.setTripName(tripModel.getTitle());
        trip.setStartPoint(tripModel.getStartPoint());
        trip.setEndPoint(tripModel.getEndPoint());
        trip.setPriorityImage(tripModel.getImportance());
        trip.setRepetition(tripModel.getRepetition());
        trip.setRounded(tripModel.getRounded());
        trip.setNotes(tripModel.getNotes());
        trip.setHistory(tripModel.getHistory());
        trip.setDateAndTime(tripModel.getDateTime());
        String dateTime=getTimeAndDate(tripModel.getDateTime());
        trip.setTime(dateTime.substring(13,18));
        trip.setDate(dateTime.substring(0,10));
        return trip;
    }

    public TripModel fromTripToTripModel(Trip trip)
    {
        TripModel tripModel = new TripModel();
        tripModel.setTitle(trip.getTripName());
        tripModel.setStartPoint(trip.getStartPoint());
        tripModel.setEndPoint(trip.getEndPoint());
        tripModel.setId(trip.getId());
        tripModel.setDateTime(trip.getDateAndTime());
        tripModel.setHistory(trip.isHistory());
        tripModel.setImportance(trip.getPriorityImage());
        tripModel.setRepetition(trip.getRepetition());
        tripModel.setNotes(trip.getNotes());
        tripModel.setRounded(trip.getRounded());
        return tripModel;
    }

    String getTimeAndDate(Long dateTime ){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   hh:mm:ss.SSS");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTime);
        return formatter.format(calendar.getTime());
    }
}
