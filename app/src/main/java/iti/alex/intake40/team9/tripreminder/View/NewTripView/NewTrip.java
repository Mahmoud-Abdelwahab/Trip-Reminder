package iti.alex.intake40.team9.tripreminder.View.NewTripView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.alex.intake40.team9.tripreminder.Models.FireBaseModel;
import iti.alex.intake40.team9.tripreminder.POJO.TripConverter;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.AlarmReciever;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.BaseAlarm;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.NewTripPresnter;
import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;
import iti.alex.intake40.team9.tripreminder.Views.NavigationDrawerView;
import iti.alex.intake40.team9.tripreminder.Views.UpcomingFragmentView;
import iti.alex.intake40.team9.tripreminder.autocomplete.PlaceAutoSuggestAdapter;

public class NewTrip extends AppCompatActivity {

    @BindView(R.id.titleTxt)
    EditText titleTxt;
    @BindView(R.id.fromAuto)
    AutoCompleteTextView fromAuto;
    @BindView(R.id.toAuto)
    AutoCompleteTextView toAuto;
    @BindView(R.id.rep)
    Spinner repetiton;
    @BindView(R.id._round)
    Spinner Round;
    @BindView(R.id._imp)
    Spinner Imp;
    @BindView(R.id.datePicker)
    ImageView date_time;
    @BindView(R.id.addBtn)
    Button addBtn;
    public static long OBJ_ID;
    private NewTripPresnter newTripPresnter;
    public static Boolean isRepeated = false;

    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    Context context;
    BaseAlarm baseAlarm;
    private TripModel tripE;
    public static Calendar myCalendar;
    TripConverter convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        ButterKnife.bind(this);
        convert = new TripConverter();
        newTripPresnter = new NewTripPresnter(this);

        context = getBaseContext();
        myCalendar = Calendar.getInstance();
        baseAlarm = new BaseAlarm(getApplicationContext());
        setFromAutoComplete();
        setToAutoComplete();
        // ///////////////////// data time    /////////
        date_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTripPresnter.showDateTimePicker();
            }
        });


        Intent intent = getIntent();
        String action = intent.getStringExtra("ACTION");
        tripE = (TripModel) intent.getSerializableExtra("TRIP");
//        tripE= (TripModel) getIntent().getParcelableExtra("TRIP");


        if (action != null && action.equals("add")) {

            // ///////////////////// data time    /////////
            addBtn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    TripModel tripe = new TripModel();
                    tripe.setTitle(titleTxt.getText().toString());

                    Calendar targetCal = Calendar.getInstance();
                    int id = (int) targetCal.getTimeInMillis();
                    tripe.setId(id);
                    Log.d("abdelwahab", "" + tripe.getId());

                    tripe.setStartPoint(fromAuto.getText().toString());
                    tripe.setEndPoint(toAuto.getText().toString());
                    tripe.setNotes(null); // add note ----------->>>>
                    tripe.setRepetition(repetiton.getSelectedItem().toString());

                    tripe.setRounded(Round.getSelectedItem().toString());
                    tripe.setImportance(Imp.getSelectedItem().toString());
                    tripe.setHistory(false);
                    long millis = myCalendar.getTimeInMillis();
                    tripe.setDateTime(millis);

                    baseAlarm.setAlarm(tripe);

                    newTripPresnter.addNewTrip(tripe);

//                    FireBaseModel.sharedInstance.addTrip(convert.fromTripModelTotrip(tripe));


                    Intent upcomming = new Intent(getApplicationContext() , NavigationDrawerView.class);
                    startActivity(upcomming);


                }
            });


        } else if (action != null && action.equals("edit")) {
            // 1- change button name to edite
            // 2 - setting all fields with object data
            // 3 - update object in database
            //4 - reset alarm
            addBtn.setText(" Edit ");
            titleTxt.setText(tripE.getTitle());
            fromAuto.setText(tripE.getStartPoint());
            toAuto.setText(tripE.getEndPoint());

            addBtn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    editTrip();

                    Intent upcomming = new Intent(getApplicationContext(), UpcomingFragmentView.class);
                    startActivity(upcomming);
                }
            });


        }

    }


    public void setFromAutoComplete() {

//        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.fromAuto);

        fromAuto.setAdapter(new PlaceAutoSuggestAdapter(this, android.R.layout.simple_list_item_1));

        fromAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ", fromAuto.getText().toString());
                LatLng latLng = getLatLngFromAddress(fromAuto.getText().toString());
                if (latLng != null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ", "" + address.getAddressLine(0));
                        Log.d("Phone : ", "" + address.getPhone());
                        Log.d("Pin Code : ", "" + address.getPostalCode());
                        Log.d("Feature : ", "" + address.getFeatureName());
                        Log.d("More : ", "" + address.getLocality());
                    } else {
                        Log.d("Adddress", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }

            }
        });

    }


    public void setToAutoComplete() {

//        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.toAuto);

        toAuto.setAdapter(new PlaceAutoSuggestAdapter(this, android.R.layout.simple_list_item_1));

        toAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ", toAuto.getText().toString());
                LatLng latLng = getLatLngFromAddress(toAuto.getText().toString());
                if (latLng != null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ", "" + address.getAddressLine(0));
                        Log.d("Phone : ", "" + address.getPhone());
                        Log.d("Pin Code : ", "" + address.getPostalCode());
                        Log.d("Feature : ", "" + address.getFeatureName());
                        Log.d("More : ", "" + address.getLocality());
                    } else {
                        Log.d("Adddress", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }

            }
        });

    }


    private LatLng getLatLngFromAddress(String address) {

        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if (addressList != null) {
                Address singleaddress = addressList.get(0);
                LatLng latLng = new LatLng(singleaddress.getLatitude(), singleaddress.getLongitude());
                return latLng;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private Address getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if (addresses != null) {
                Address address = addresses.get(0);
                return address;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void editTrip() {
        if (!titleTxt.getText().equals("") && !fromAuto.getText().equals("") && !toAuto.getText().equals("") && tripE.getDateTime() != 0) {
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent activate = new Intent(context, AlarmReciever.class);
//        activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    tripE.getId(), activate, PendingIntent.FLAG_UPDATE_CURRENT);
            manager.cancel(pendingIntent);
            tripE.setTitle(titleTxt.getText().toString());
            tripE.setStartPoint(fromAuto.getText().toString());
            tripE.setEndPoint(toAuto.getText().toString());
            tripE.setNotes(null); // add note ----------->>>>
            tripE.setRepetition(repetiton.getSelectedItem().toString());
            tripE.setRounded(Round.getSelectedItem().toString());
            tripE.setImportance(Imp.getSelectedItem().toString());
            tripE.setHistory(false);
            long millis = myCalendar.getTimeInMillis();
            tripE.setDateTime(millis);

            Log.i("object ", "datatime " + tripE.getDateTime() + " state " + tripE.getStartPoint()
                    + " end point " + tripE.getEndPoint());

            baseAlarm.setAlarm(tripE);
            DbModel db = new DbModel(getApplicationContext());
            db.updateTripDb(tripE);
//            FireBaseModel.sharedInstance.addTrip(convert.fromTripModelTotrip(tripE));
        } else {
            Toast.makeText(context.getApplicationContext(),
                    " Fill Empty Fields ", Toast.LENGTH_LONG).show();
        }

    }

}
