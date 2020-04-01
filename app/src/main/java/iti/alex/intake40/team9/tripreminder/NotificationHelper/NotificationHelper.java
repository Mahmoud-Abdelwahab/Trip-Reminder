package iti.alex.intake40.team9.tripreminder.NotificationHelper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.View.ActivityDialog.MyDialogeActivity;

public class NotificationHelper extends ContextWrapper {


        private Context mContext;
        private NotificationManager mNotificationManager;
        private NotificationCompat.Builder mBuilder;
        public static final String NOTIFICATION_CHANNEL_ID = "10001";

        public NotificationHelper(Context context) {
            super(context);
            mContext = context;
        }

        /**
         * Create and push the notification
         */
        public void createNotification(String title, String message)
        {
            /**Creates an explicit intent for an Activity in your app**/
            Intent resultIntent = new Intent(mContext , MyDialogeActivity.class);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                    0 /* Request code */, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder = new NotificationCompat.Builder(mContext);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(false)   .setOngoing(true)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setContentIntent(resultPendingIntent);
            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Fakarny", importance);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                assert mNotificationManager != null;

                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
                mNotificationManager.createNotificationChannel(notificationChannel);
            }



            assert mNotificationManager != null;
            mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
        }
    }