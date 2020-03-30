package iti.alex.intake40.team9.tripreminder.View.ActivityDialog;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.AlarmReciever;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.AlarmServiceDialog;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.BaseAlarm;
import iti.alex.intake40.team9.tripreminder.R;

public class MyDialogeActivity extends AppCompatActivity {
    AlarmReciever alarmReciever;
    MediaPlayer myPlayer;
    @BindView(R.id.snoozeBtn)
    Button snoozeBtn;
    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.cancelBtn)
    Button cancelBtn;
BaseAlarm baseAlarm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialoge);
        baseAlarm =new BaseAlarm(getApplicationContext());
        ButterKnife.bind(this);
        alarmReciever = new AlarmReciever();
        myPlayer = MediaPlayer.create(this, R.raw.media);
        myPlayer.start();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        " start", Toast.LENGTH_LONG).show();

                Uri gmmIntentUri = Uri.parse("google.navigation:q=Mahta Al Raml SQ.");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }


                IntentFilter filter = new IntentFilter("iti.alex.intake40.team9.AlarmReciever");
                registerReceiver(alarmReciever, filter);
                Intent broadCastIntent = new Intent("iti.alex.intake40.team9.AlarmReciever");
                broadCastIntent.putExtra("Action", "stop");
                broadCastIntent.setAction("iti.alex.intake40.team9.AlarmReciever");
                sendBroadcast(broadCastIntent);
                myPlayer.stop();
                Intent myService = new Intent(getApplicationContext(), AlarmServiceDialog.class);
                stopService(myService);
                finish();

            }
        });

        snoozeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        " snoozing", Toast.LENGTH_LONG).show();

                IntentFilter filter = new IntentFilter("iti.alex.intake40.team9.AlarmReciever");
                registerReceiver(alarmReciever, filter);
                Intent broadCastIntent = new Intent("iti.alex.intake40.team9.AlarmReciever");
                broadCastIntent.putExtra("Action", "wait");
                broadCastIntent.setAction("iti.alex.intake40.team9.AlarmReciever");
                sendBroadcast(broadCastIntent);

                myPlayer.stop();
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        " Canceling ...", Toast.LENGTH_LONG).show();

//                IntentFilter filter = new IntentFilter("iti.alex.intake40.team9.AlarmReciever");
//                registerReceiver(alarmReciever, filter);
//                Intent broadCastIntent = new Intent("iti.alex.intake40.team9.AlarmReciever");
//                broadCastIntent.putExtra("Action", "stop");
//                broadCastIntent.setAction("iti.alex.intake40.team9.AlarmReciever");
//                sendBroadcast(broadCastIntent);
                Intent myService = new Intent(getApplicationContext(), AlarmServiceDialog.class);
                stopService(myService);


//                String ns = Context.NOTIFICATION_SERVICE;
//                NotificationManager nMgr = (NotificationManager) getBaseContext().getSystemService(ns);
//                nMgr.cancel(0);
                myPlayer.stop();


                finish();
            }
        });


    }


    public void cancelAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent activate = new Intent(this, AlarmReciever.class);
        activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                777, activate,
                PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}