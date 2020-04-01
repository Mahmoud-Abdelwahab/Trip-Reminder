package iti.alex.intake40.team9.tripreminder.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import iti.alex.intake40.team9.tripreminder.Views.LoginView;
import iti.alex.intake40.team9.tripreminder.Views.NavigationDrawerView;

public class RegistrationPresenter {

    private Context context;
    private FirebaseAuth mAuth;

    public RegistrationPresenter(Context context) {
        this.context = context;
        mAuth = mAuth = FirebaseAuth.getInstance();
    }

    public void onRegisterClicked(final Bitmap bitmap, String email, String passwd, String confirm) {
        if (passwd.equals(confirm))
        {
            mAuth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Registered successfully", Toast.LENGTH_LONG).show();
                        if (bitmap != null)
                        {
                            FirebaseStorage storage = FirebaseStorage.getInstance();
                            StorageReference storageRef = storage.getReference().child(mAuth.getUid());
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] data = baos.toByteArray();

                            UploadTask uploadTask = storageRef.putBytes(data);
                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Toast.makeText(context, "Couldn't upload the image.", Toast.LENGTH_LONG).show();
                                }
                            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(context, "Registered successfully.", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(context, NavigationDrawerView.class);
                                    context.startActivity(i);
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(context, "You didn't add image.", Toast.LENGTH_LONG);
                            Intent i = new Intent(context, NavigationDrawerView.class);
                            context.startActivity(i);
                        }
                    }
                    else
                        Toast.makeText(context, "Register failed.\nPlease check your internet connection.", Toast.LENGTH_LONG).show();
                }
            });
        }
        else
            Toast.makeText(context, "Passwords don't match.", Toast.LENGTH_LONG).show();

    }
}
