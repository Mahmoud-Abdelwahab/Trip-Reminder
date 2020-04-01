package iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.format.DateFormat;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;
import iti.alex.intake40.team9.tripreminder.View.NewTripView.NewTrip;

import static iti.alex.intake40.team9.tripreminder.View.NewTripView.NewTrip.isRepeated;

public class BaseAlarm   {

    DbModel dbModel;
    Context context ;
     public BaseAlarm( Context context)
     {this.context = context;
     dbModel = new DbModel(context);
     }




////////////////// set alarm  ****************///
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ShortAlarm")
    public void setAlarm(TripModel trip) {


        Calendar targetCal = Calendar.getInstance();
        targetCal.setTimeInMillis(trip.getDateTime());
        int pendingIntent_ID = trip.getId();

//        trip.setId(pendingIntent_ID);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent activate = new Intent(context, AlarmReciever.class);
        activate.putExtra("intent_ID",pendingIntent_ID); // to  get object

        //   activate.setAction("iti.alex.intake40.team9.AlarmReciever");
        //  activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //  activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        int _id = (int) System.currentTimeMillis();
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, pendingIntent_ID, activate, PendingIntent.FLAG_UPDATE_CURRENT);

        // create isRepeated in your code do your stufff mahoud hahahhahah
         String rep =   trip.getRepetition();

        if (rep.equals("")) {

//            if (Build.VERSION.SDK_INT < 23) {
//                if (Build.VERSION.SDK_INT >= 19) {
//                    manager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
//                } else {
//                    manager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
//                }
//            } else {
//                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
//            }
            if (Build.VERSION.SDK_INT >= 23) {
                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
            } else if (Build.VERSION.SDK_INT >= 19) {
                manager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
            } else {
                manager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
            }

        } else  if(rep.equals("Weakly"))
            {
//            Calendar today = Calendar.getInstance();
            Long difer = System.currentTimeMillis() + targetCal.getTimeInMillis();
            //   manager.setRepeating(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), daysBetween(today, targetCal), alarmIntent);
            manager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), 24 * 7 * 60 * 60 * 1000, alarmIntent);
        }else  if(rep.equals("Monthly"))
        {
            GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
            if(cal.isLeapYear(targetCal.get(Calendar.YEAR))){//for leap year feburary month
                manager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), 24 * 30 * 60 * 60 * 1000, alarmIntent);
                Toast.makeText(context, "februry", Toast.LENGTH_SHORT).show();}
            else{ //for non leap year feburary month
                Toast.makeText(context, "feb", Toast.LENGTH_SHORT).show();
                manager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), 24 * 30 * 60 * 60 * 1000, alarmIntent);
            }
        }

        Toast.makeText(context.getApplicationContext(),
                " setting Alarm" + targetCal.getTime(), Toast.LENGTH_LONG).show();
//        ComponentName receiver = new ComponentName(context, AlarmReciever.class);
//        PackageManager pm = context.getPackageManager();
//
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);



    }







    /////////////////////////********** Edite ******************////

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public  void editeAlarm(TripModel tripModel)
    {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent activate = new Intent(context, AlarmReciever.class);
//        activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                tripModel.getId(), activate,
                PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);

//        DbModel dbModel = new DbModel(context);
//
//        dbModel.updateTripDb(tripModel);

              setAlarm(tripModel);

    }


    //////***************  cancel   ***********
    public  void cancelAlarm( TripModel tripModel){
         tripModel.setHistory(true); /// add it to history
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent activate = new Intent(context, AlarmReciever.class);
//        activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                tripModel.getId(), activate,
                PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);

//        dbModel.deleteTripDb(tripModel);

    }

   String getTimeAndDate(Long dateTime ){
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   hh:mm:ss.SSS");

       Calendar calendar = Calendar.getInstance();
       calendar.setTimeInMillis(dateTime);
       return formatter.format(calendar.getTime());
   }




}
