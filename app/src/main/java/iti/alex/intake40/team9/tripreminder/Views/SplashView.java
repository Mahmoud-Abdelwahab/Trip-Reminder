package iti.alex.intake40.team9.tripreminder.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import iti.alex.intake40.team9.tripreminder.Presenters.SplashPresenter;
import iti.alex.intake40.team9.tripreminder.R;

public class SplashView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new SplashPresenter(this);
        finish();
    }
}
