package iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import iti.alex.intake40.team9.tripreminder.View.NewTripView.NewTrip;

public class MyTimePicker  extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
 NewTrip main;
     public  MyTimePicker(NewTrip mainacrivirt){
       main = mainacrivirt ;
     }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user


        NewTrip.myCalendar.set(Calendar.HOUR_OF_DAY , hourOfDay);
        NewTrip.myCalendar.set(Calendar.MINUTE ,minute);
        NewTrip.myCalendar.set(Calendar.SECOND , 0);
           //// DO your staff

//        Toast.makeText(getActivity().getApplicationContext() , "Done " +   MainActivity.myCalendar.getTime(), Toast.LENGTH_LONG).show();
//    Log.i("Alarm ---> " , MainActivity.myCalendar.getTime().toString());


        if (NewTrip.myCalendar.compareTo(Calendar.getInstance()) <= 0) {
            // The set Date/Time already passed
            Toast.makeText(getContext().getApplicationContext(),
                    "Invalid Date/Time", Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(getContext().getApplicationContext(),
               //     ""+MainActivity.myCalendar.getTime(), Toast.LENGTH_LONG).show();

              //  main.getCalendar(NewTrip.myCalendar);

        }

    }





}

