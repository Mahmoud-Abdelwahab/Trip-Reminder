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
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.SettingCalendar;
import iti.alex.intake40.team9.tripreminder.Presenter.NewTripPresenter.NewTripPresnter;
import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;
import iti.alex.intake40.team9.tripreminder.autocomplete.PlaceAutoSuggestAdapter;

public class NewTrip extends AppCompatActivity {
  String rep;
  String roun;
    @BindView(R.id.autoComp_From)
    AutoCompleteTextView autoCompFrom;
    @BindView(R.id.roundSpiner)
    Spinner roundSpiner;
    @BindView(R.id.autoComp_To)
    AutoCompleteTextView autoCompTo;
    @BindView(R.id.repeatSpinner)
    Spinner repeatSpinner;
    @BindView(R.id.addBtn)
    Button addBtn;
    @BindView(R.id.dateTime)
    ImageView dateTime;
    @BindView(R.id.titleTxt)
    EditText titleTxt;
    private NewTripPresnter newTripPresnter;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_SETTINGS = 1001;
    public  static Boolean isRepeated = false;
    Spinner repetition;
    Spinner rounded;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    Context context;



    public static Calendar myCalendar;
//    public static  int pendinIntentID ;
    List<Integer> alaramID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        ButterKnife.bind(this);

        initalizeSpinners();
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
               int  pendingID = (int) System.currentTimeMillis();
                tripe.setId(pendingID);
                tripe.setStartPoint(autoCompFrom.toString());
                tripe.setEndPoint(autoCompTo.toString());
                tripe.setNotes(null); // add note ----------->>>>
                tripe.setRepetition(repetition.getSelectedItem().toString());

                tripe.setRounded(rounded.getSelectedItem().toString());
                tripe.setHistory(false);
                tripe.setDateTime(myCalendar.getTimeInMillis()+"");
                 new BaseAlarm(getApplicationContext()).setAlarm(myCalendar ,pendingID );


                newTripPresnter.addNewTrip(tripe);
            }
        });


        // ///////////////////// data time    /////////
        dateTime.setOnClickListener(new View.OnClickListener() {
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



        setFromAutoComplete();
        setToAutoComplete();



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


    void initalizeSpinners() {
//        repetition = (Spinner) findViewById(R.id.repeatSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.repetition_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        repeatSpinner.setAdapter(adapter);


//        rounded = (Spinner) findViewById(R.id.roundSpiner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.rounded_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        roundSpiner.setAdapter(adapter);


    }

    private   String getRepetion(){

       repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              rep = parent.getItemAtPosition(position).toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
         return rep;
    }

//    @Override
//    public void getCalendar(Calendar calendar) {
//
//
//    }




    public  void setFromAutoComplete()
    {

        final AutoCompleteTextView autoCompleteTextView=findViewById(R.id.autoComp_From);

        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(this,android.R.layout.simple_list_item_1));

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });

    }



     public  void setToAutoComplete()
     {

         final AutoCompleteTextView autoCompleteTextView=findViewById(R.id.autoComp_To);

         autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(this,android.R.layout.simple_list_item_1));

         autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Log.d("Address : ",autoCompleteTextView.getText().toString());
                 LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                 if(latLng!=null) {
                     Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                     Address address=getAddressFromLatLng(latLng);
                     if(address!=null) {
                         Log.d("Address : ", "" + address.toString());
                         Log.d("Address Line : ",""+address.getAddressLine(0));
                         Log.d("Phone : ",""+address.getPhone());
                         Log.d("Pin Code : ",""+address.getPostalCode());
                         Log.d("Feature : ",""+address.getFeatureName());
                         Log.d("More : ",""+address.getLocality());
                     }
                     else {
                         Log.d("Adddress","Address Not Found");
                     }
                 }
                 else {
                     Log.d("Lat Lng","Lat Lng Not Found");
                 }

             }
         });

     }


    private LatLng getLatLngFromAddress(String address){

        Geocoder geocoder=new Geocoder(this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if(addressList!=null){
                Address singleaddress=addressList.get(0);
                LatLng latLng=new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                return latLng;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private Address getAddressFromLatLng(LatLng latLng){
        Geocoder geocoder=new Geocoder(this);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if(addresses!=null){
                Address address=addresses.get(0);
                return address;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
