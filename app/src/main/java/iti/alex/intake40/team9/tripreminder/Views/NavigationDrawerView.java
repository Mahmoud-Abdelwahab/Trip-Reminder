package iti.alex.intake40.team9.tripreminder.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Models.FireBaseModel;
import iti.alex.intake40.team9.tripreminder.Models.FloatingItem;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.POJO.TripConverter;
import iti.alex.intake40.team9.tripreminder.Presenters.UpcomingFragmentPresenter;
import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.Room.DbModel;
import iti.alex.intake40.team9.tripreminder.Room.TripModel;

public class NavigationDrawerView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    DrawerLayout drawer;
    View navHeader;
    TextView email;
    ImageView profilePicture;
    private UpcomingFragmentView upcomingFragmentView;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       View headerView;
//        navHeader=getLayoutInflater().inflate(R.layout.nav_header,null);
       Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        email=headerView.findViewById(R.id.uemail);
        profilePicture=headerView.findViewById(R.id.profile_image);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        Log.i("ay7aga", FireBaseModel.sharedInstance.getUserEmail());
        email.setText(FireBaseModel.sharedInstance.getUserEmail());
        Bitmap bitmap = FireBaseModel.sharedInstance.getProfileImage();
        profilePicture.setImageBitmap(FireBaseModel.sharedInstance.getProfileImage());
        toggle.syncState();

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UpcomingFragmentView()).commit();
            navigationView.setCheckedItem(R.id.nav_upcoming);

        }
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_upcoming:
                upcomingFragmentView=new UpcomingFragmentView();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,upcomingFragmentView).commit();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HistoryFragmentView()).commit();
                break;
            case R.id.nav_sync:

                List<Trip> trips= new ArrayList<>();
                trips =FireBaseModel.sharedInstance.getTrips();
                for(int i=0; i<trips.size();i++)
                {
                    FireBaseModel.sharedInstance.deleteTrip(trips.get(i));
                }
                trips=getTripsFromRoom();
                for(int i=0; i<trips.size();i++)
                {
                    FireBaseModel.sharedInstance.addTrip(trips.get(i));
                }
                break;
            case R.id.nav_logout:
                FireBaseModel.sharedInstance.signOut();
                Intent i = new Intent(this, LoginView.class);
                startActivity(i);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
                startService(new Intent(this, FloatingItem.class));
                finish();
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public List<Trip> getTripsFromRoom() {
        List<TripModel> tripModels = new ArrayList<>();
        List<Trip> trips= new ArrayList<>();

        DbModel dbModel = new DbModel(this);
        tripModels=dbModel.getAllTripDb();
        for(int i=0 ; i<tripModels.size();i++)
        {
            Trip trip=new Trip();
            TripConverter tripConverter = new TripConverter();
            trips.add(tripConverter.fromTripModelTotrip(tripModels.get(i)));
        }

        return trips;
    }


}
