package iti.alex.intake40.team9.tripreminder.View.ActivityDialog;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.alex.intake40.team9.tripreminder.Adapters.UpcomingRecyclarViewAdapter;
import iti.alex.intake40.team9.tripreminder.Models.FloatingItem;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.AlarmReciever;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.AlarmServiceDialog;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.BaseAlarm;
import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;
import iti.alex.intake40.team9.tripreminder.View.NewTripView.NewTrip;

public class MyDialogeActivity extends AppCompatActivity {
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    AlarmReciever alarmReciever;
    MediaPlayer myPlayer;
    @BindView(R.id.snoozeBtn)
    Button snoozeBtn;
    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.cancelBtn)
    Button cancelBtn;
BaseAlarm baseAlarm ;

int OBJ_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialoge);
        baseAlarm =new BaseAlarm(getApplicationContext());
        ButterKnife.bind(this);
        alarmReciever = new AlarmReciever();
        myPlayer = MediaPlayer.create(this, R.raw.media);
        myPlayer.setLooping(true);
        myPlayer.start();

        OBJ_ID=getIntent().getIntExtra("intent_ID",4);
//     OBJ_ID=AlarmReciever.shared_id;
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        " start", Toast.LENGTH_LONG).show();


//                Uri gmmIntentUri = Uri.parse("google.navigation:q=Mahta Al Raml SQ.");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                if (mapIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(mapIntent);
//                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(MyDialogeActivity.this)) {

                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" +  getPackageName()));
                    startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
                } else {
                    DbModel dbModel = new DbModel(getApplicationContext());
                    TripModel trip = dbModel.getTripByID(OBJ_ID);
                    Intent intent = new Intent(MyDialogeActivity.this, FloatingItem.class);
                    String [] notes = trip.getNotes().toArray(new String[trip.getNotes().size()]);
                    intent.putExtra("notes",notes);
                    startService(intent);
                    finish();
                }

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


                myPlayer.stop();
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        " Canceling ...", Toast.LENGTH_LONG).show();

                DbModel dbModel = new DbModel(getApplicationContext());
                TripModel trip = dbModel.getTripByID(OBJ_ID);
//                TripModel trip = dbModel.getTripByID(AlarmReciever.shared_id);

                trip.setHistory(true);
                trip.setStatus("Canceled");
                dbModel.updateTripDb(trip);

                AlarmManager manager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent activate = new Intent(getApplicationContext(), AlarmReciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                        trip.getId(), activate,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                manager.cancel(pendingIntent);


                Intent myService = new Intent(getApplicationContext(), AlarmServiceDialog.class);
                stopService(myService);

                myPlayer.stop();


                finish();
}
        });


                }





@Override
    protected void onDestroy() {
        super.onDestroy();
    }
}