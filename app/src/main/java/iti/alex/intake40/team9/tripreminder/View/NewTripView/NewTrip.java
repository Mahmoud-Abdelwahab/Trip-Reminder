package iti.alex.intake40.team9.tripreminder.View.NewTripView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.BaseAlarm;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.NewTripPresnter;
import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;
import iti.alex.intake40.team9.tripreminder.autocomplete.PlaceAutoSuggestAdapter;

public class NewTrip extends AppCompatActivity {
    String rep;
    String roun;
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
public  static  int OBJ_ID;
    private NewTripPresnter newTripPresnter;
    public static Boolean isRepeated = false;

    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    Context context;
    BaseAlarm baseAlarm ;

    public static Calendar myCalendar;
    //    public static  int pendinIntentID ;
    List<Integer> alaramID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        ButterKnife.bind(this);
 baseAlarm = new BaseAlarm(getApplicationContext());
        setFromAutoComplete();
        setToAutoComplete();







        newTripPresnter = new NewTripPresnter(this);
        alaramID = new ArrayList<Integer>();

        context = getBaseContext();
        myCalendar = Calendar.getInstance();



        // ///////////////////// data time    /////////
        addBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                TripModel tripe = new TripModel();
                tripe.setTitle(titleTxt.getText().toString());

                tripe.setStartPoint(fromAuto.getText().toString());
                tripe.setEndPoint(toAuto.getText().toString());
                tripe.setNotes(null); // add note ----------->>>>
                tripe.setRepetition(repetiton.getSelectedItem().toString());

                tripe.setRounded(Round.getSelectedItem().toString());
                tripe.setImportance(Imp.getSelectedItem().toString());
                tripe.setHistory(false);
                long millis = myCalendar.getTimeInMillis() ;
                tripe.setDateTime(millis);

                Log.i("object " , "datatime "+tripe.getDateTime() +" state " + tripe.getStartPoint()
                 +" end point " + tripe.getEndPoint());

                baseAlarm.setAlarm(tripe);
                DbModel db = new DbModel(getApplicationContext());
                 List<TripModel> trips =  db.getAllTripDb();

               newTripPresnter.addNewTrip(tripe);
            }
        });


        // ///////////////////// data time    /////////
        date_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTripPresnter.showDateTimePicker();
            }
        });


////////////////////// edit   /////////////////////////
//
//        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//                Intent activate = new Intent(context, AlarmReciever.class);
////                activate.setAction("es.monlau.smartschool.AlarmReceiver");
////                activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//
//                if( alaramID.get(0) != null) {
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
//                            alaramID.get(0), activate,
//                            PendingIntent.FLAG_UPDATE_CURRENT);
//                    manager.cancel(pendingIntent);
//                    alaramID.remove(0);
//                    DialogFragment newFragment = new MyDatePicker(MainActivity.this);
//                    newFragment.show(getSupportFragmentManager(), "datePicker");
//            }
//        }
//    });


        ////////////////////////  Cancel   /////////////
//    findViewById(R.id.cancelBtn).
//
//    setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick (View v){
//            cancelAlarm( int pendingIntent_ID);
//        }
//    });


    }


//    public  void cancelAlarm(){
//        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent activate = new Intent(context, AlarmReciever.class);
////        activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
//                777, activate,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        manager.cancel(pendingIntent);
//    }


//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    @Override
//    public void getCalendar(Calendar calendar) {
//
//
//        setAlarm(calendar);
//    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    @SuppressLint("ShortAlarm")
//    private void setAlarm(Calendar targetCal ) {
//
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent activate = new Intent(context, AlarmReciever.class);
//
//
//        //   activate.setAction("iti.alex.intake40.team9.AlarmReciever");
//        //  activate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //  activate.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//        int _id = (int) System.currentTimeMillis();
//        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, _id, activate, PendingIntent.FLAG_UPDATE_CURRENT);
//        alaramID.add(_id);
//        // create isRepeated in your code do your stufff mahoud hahahhahah
//        if (!isRepeated) {
//
//            if (Build.VERSION.SDK_INT < 23) {
//                if (Build.VERSION.SDK_INT >= 19) {
//                    manager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
//                } else {
//                    manager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
//                }
//            } else {
//                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmIntent);
//            }
//
//        } else {
////            Calendar today = Calendar.getInstance();
//            Long difer = System.currentTimeMillis() + targetCal.getTimeInMillis();
//            //   manager.setRepeating(AlarmManager.RTC_WAKEUP, today.getTimeInMillis(), daysBetween(today, targetCal), alarmIntent);
//            manager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), difer, alarmIntent);
//        }
//
//        Toast.makeText(getApplicationContext(),
//                " setting Alarm" + targetCal.getTime(), Toast.LENGTH_LONG).show();
//        ComponentName receiver = new ComponentName(context, AlarmReciever.class);
//        PackageManager pm = context.getPackageManager();
//
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);
//
//
//    }



//    @Override
//    public void getCalendar(Calendar calendar) {
//
//
//    }


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

}
