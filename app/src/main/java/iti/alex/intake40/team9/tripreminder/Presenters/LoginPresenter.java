package iti.alex.intake40.team9.tripreminder.Presenters;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import iti.alex.intake40.team9.tripreminder.Views.NavigationDrawerView;
import iti.alex.intake40.team9.tripreminder.Views.RegisterationView;

public class LoginPresenter {

    private FirebaseAuth mAuth;
    private Context context;

    public LoginPresenter(Context context){
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public void onLoginClicked(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent i = new Intent(context, NavigationDrawerView.class);
                    context.startActivity(i);
                    Toast.makeText(context, "Login succeeded", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onRegisterClicked(){
        Intent i = new Intent(context, RegisterationView.class);
        context.startActivity(i);
    }
}
