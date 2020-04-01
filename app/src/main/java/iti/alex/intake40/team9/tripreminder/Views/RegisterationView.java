package iti.alex.intake40.team9.tripreminder.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import iti.alex.intake40.team9.tripreminder.Presenters.RegistrationPresenter;
import iti.alex.intake40.team9.tripreminder.R;


public class RegisterationView extends AppCompatActivity {

    ImageView iv;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        final RegistrationPresenter rp = new RegistrationPresenter(this);
        iv = findViewById(R.id.add_image);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, 123);
            }
        });
        final EditText email = findViewById(R.id.email_reg);
        final EditText pass = findViewById(R.id.pass_reg);
        final EditText confirm = findViewById(R.id.confirm_pass_reg);
        Button reg = findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ANE", "onclick" + bitmap.toString());
                rp.onRegisterClicked(bitmap, email.getText().toString(), pass.getText().toString(), confirm.getText().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            iv.setBackground(null);
            iv.setImageURI(fullPhotoUri);
            iv.setDrawingCacheEnabled(true);
            iv.buildDrawingCache();
            bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
            Log.i("ANE", bitmap.toString());
        }
    }
}
