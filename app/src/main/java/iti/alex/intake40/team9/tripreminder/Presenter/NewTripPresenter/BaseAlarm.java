package iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import iti.alex.intake40.team9.tripreminder.View.NewTripView.NewTrip;

public class BaseAlarm   {


    Context context ;

     public BaseAlarm( Context context)
     {this.context = context;
     }


     //////***************  cancel   ***********
    public  void cancelAlarm(int pendingIntent_ID){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent activate = new Intent(context, AlarmReciever.class);
//        activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
         pendingIntent_ID, activate,
                PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);
    }



////////////////// set alarm  ****************///
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ShortAlarm")
    public void setAlarm(Calendar targetCal, int pendingIntent_ID ) {

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent activate = new Intent(context, AlarmReciever.class);


        //   activate.setAction("iti.alex.intake40.team9.AlarmReciever");
        //  activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //  activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        NewTrip.pendinIntentID = (int) System.currentTimeMillis();
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context,  pendingIntent_ID, activate, PendingIntent.FLAG_UPDATE_CURRENT);
      //  alaramID.add(_id);
        // create isRepeated in your code do your stufff mahoud hahahhahah
        if (!NewTrip.isRepeated) {

            if (Build.VERSION.SDK_INT < 23) {
                if (Build.VERSION.SDK_INT >= 19) {
                    manager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
                } else {
                    manager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
                }
            } else {
                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
            }

        } else {
//            Calendar today = Calendar.getInstance();
            Long difer = System.currentTimeMillis() + targetCal.getTimeInMillis();
            //   manager.setRepeating(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), daysBetween(today, targetCal), alarmIntent);
         //   manager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), difer, alarmIntent);
            manager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),  24 * 7 * 60 * 60 * 1000, alarmIntent);



        }

        Toast.makeText(context,
                " setting Alarm" + targetCal.getTime(), Toast.LENGTH_LONG).show();
        ComponentName receiver = new ComponentName(context, AlarmReciever.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);


    }







    /////////////////////////********** Edite ******************////

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  void editeAlarm(Calendar calendar, int pendingIntent_ID_Old, int pendingIntent_ID_New )
    {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent activate = new Intent(context, AlarmReciever.class);
//                activate.setAction("es.monlau.smartschool.AlarmReceiver");
//                activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);


                   cancelAlarm(pendingIntent_ID_Old);

                 //   alaramID.remove(0);
//
//                    DialogFragment newFragment = new MyDatePicker(new_trip);
//                    newFragment.show(new_trip.getSupportFragmentManager(), "datePicker");
              setAlarm(calendar,pendingIntent_ID_New);

    }

}
