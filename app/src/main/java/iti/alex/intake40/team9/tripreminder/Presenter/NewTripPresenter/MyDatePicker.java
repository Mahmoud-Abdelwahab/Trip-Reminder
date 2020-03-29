package iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import iti.alex.intake40.team9.tripreminder.View.NewTripView.NewTrip;

public class MyDatePicker extends DialogFragment
                 implements DatePickerDialog.OnDateSetListener {
    NewTrip main ;
    public  MyDatePicker(NewTrip newTrip){
       this.main = newTrip ;
    }

@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
        }

public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
    NewTrip.myCalendar.set(Calendar.YEAR , year);
    NewTrip. myCalendar.set(Calendar.MONTH , month);
    NewTrip.myCalendar.set(Calendar.DAY_OF_MONTH , day);

    DialogFragment newFragment = new MyTimePicker(main);
    newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");


        }
        }