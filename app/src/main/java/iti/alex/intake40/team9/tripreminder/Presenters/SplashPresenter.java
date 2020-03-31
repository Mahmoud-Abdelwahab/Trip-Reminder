package iti.alex.intake40.team9.tripreminder.Presenters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import iti.alex.intake40.team9.tripreminder.Models.FireBaseModel;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.POJO.TripLocation;
import iti.alex.intake40.team9.tripreminder.Views.LoginView;
import iti.alex.intake40.team9.tripreminder.Views.NavigationDrawerView;

public class SplashPresenter {

    private Context context;
    private FirebaseAuth mAuth;

    public SplashPresenter(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        authenticate();
    }

    private void authenticate() {
        FirebaseUser fbu = mAuth.getCurrentUser();
        if (fbu == null)
        {
            Intent i = new Intent(context, LoginView.class);
            context.startActivity(i);
        }
        else
        {
            Intent i = new Intent(context, NavigationDrawerView.class);
            context.startActivity(i);
        }
    }
}