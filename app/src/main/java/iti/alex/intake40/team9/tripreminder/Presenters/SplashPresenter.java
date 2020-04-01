package iti.alex.intake40.team9.tripreminder.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Models.Delegate;
import iti.alex.intake40.team9.tripreminder.Models.FireBaseModel;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Views.LoginView;
import iti.alex.intake40.team9.tripreminder.Views.NavigationDrawerView;

public class SplashPresenter implements Delegate {

    private Activity activity;
    //private Context context;
    private FirebaseAuth mAuth;
    private FireBaseModel fireBaseModel;

    public SplashPresenter(Activity activity) {
        this.activity = activity;
        mAuth = FirebaseAuth.getInstance();
        authenticate();
    }

    private void authenticate() {
        FirebaseUser fbu = mAuth.getCurrentUser();
        if (fbu == null)
        {
            Intent i = new Intent(activity, LoginView.class);
            activity.startActivity(i);
            activity.finish();
        }
        else
        {
            fireBaseModel = FireBaseModel.sharedInstance;
            fireBaseModel.setDelegate(this);
            //fireBaseModel.getProfileImage();
        }
    }

    @Override
    public void done() {
        //Log.i("ANE", fireBaseModel.getProfileImage().toString());
        //fireBaseModel.deleteAll();
        Intent i = new Intent(activity, NavigationDrawerView.class);
        activity.startActivity(i);
        activity.finish();
    }
}