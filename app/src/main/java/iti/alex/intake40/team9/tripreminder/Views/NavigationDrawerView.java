package iti.alex.intake40.team9.tripreminder.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import iti.alex.intake40.team9.tripreminder.Models.FloatingItem;
import iti.alex.intake40.team9.tripreminder.R;

public class NavigationDrawerView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    DrawerLayout drawer;
    private UpcomingFragmentView upcomingFragmentView;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
                Toast.makeText(this, "This is Sync Button!",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_logout:
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


}
