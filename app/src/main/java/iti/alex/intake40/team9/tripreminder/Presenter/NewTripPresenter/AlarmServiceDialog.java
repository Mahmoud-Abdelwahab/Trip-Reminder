package iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import iti.alex.intake40.team9.tripreminder.NotificationHelper.NotificationHelper;
import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.View.ActivityDialog.MyDialogeActivity;

public class AlarmServiceDialog extends Service {

    public static int shared_I;

    //Used for Bound service,At this point let's keep it null
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static final String CHANNEL_ID = "myCannel";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String recvivedAction = intent.getStringExtra("Action");

        if (recvivedAction == null) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationHelper notificationHelper = new NotificationHelper(this);
                notificationHelper.createNotification("Fakarny ", "Remember your Trip..... !");
            } else {
                Intent notificationIntent = new Intent(this, MyDialogeActivity.class);

                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

                Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("  Fakarny ")
                        .setContentText("Remember your Trip..... !")
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentIntent(pIntent)
                        .setAutoCancel(false)
                        .setOngoing(true)
                        .build();

                startForeground(1, notification);


            }
            shared_I = intent.getIntExtra("intent_ID",0) ;

            Intent dialogIntent = new Intent(this, MyDialogeActivity.class);
            dialogIntent.putExtra("intent_ID",intent.getIntExtra("intent_ID",0));
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);

        }

//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//          createNotificationChannel();
//        } else {
//            addNotification();
//
//        }


        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
//        myPlayer.stop();
    }

    private void ChannedNotification() {

        Intent notificationIntent = new Intent(this, MyDialogeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("Trip Reminder ")
                .setContentText("Remember your Tripe")
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setLights(1, 5, 10)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
    }


    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MyDialogeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());


    }


    private void createNotificationChannel() {

        final String NOTIFICATION_CHANNEL_ID = "channel_id";
        final String CHANNEL_NAME = "Notification Channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, importance);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setVibrationPattern(new long[]{500, 500, 500, 500, 500});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }


    }

}