package iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AlarmReciever  extends BroadcastReceiver
        {
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
        IntentFilter filter = new IntentFilter("iti.alex.intake40.team9.AlarmReciever");

        Intent alarmServiceIntent = new Intent(context, AlarmServiceDialog.class);
//        AlarmServiceDialog.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
       String  action = intent.getAction();

        if(action !=null){
          if(action.equals("iti.alex.intake40.team9.AlarmReciever"))
          {
              String receivedAction = intent.getStringExtra("Action");

              if( receivedAction.equals("run"))
              {
                  alarmServiceIntent.putExtra("Action","run");

              }else if( receivedAction.equals("wait"))
              {
                  alarmServiceIntent.putExtra("Action","wait");

              } else  if( receivedAction.equals("stop"))
              {
                  alarmServiceIntent.putExtra("Action","stop");
              }
          }
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //createNotificationChannel();
            context.startForegroundService(alarmServiceIntent);
        } else {
           // addNotification();
            context.startService(alarmServiceIntent);

        }

    }

//    private void addNotification() {
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(context)
//                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
//                        .setContentTitle("Notifications Example")
//                        .setContentText("This is a test notification");
//
//        Intent notificationIntent = new Intent(context, Second.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
//    }

//
//    private void createNotificationChannel() {
//
//        final String NOTIFICATION_CHANNEL_ID = "channel_id";
//        final String CHANNEL_NAME = "Notification Channel";
//        int importance = NotificationManager.IMPORTANCE_DEFAULT;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, importance);
//            notificationChannel.enableLights(true);
//            notificationChannel.enableVibration(true);
//            notificationChannel.setLightColor(Color.GREEN);
//            notificationChannel.setVibrationPattern(new long[]{500, 500, 500, 500, 500});
//            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//
//    }


}