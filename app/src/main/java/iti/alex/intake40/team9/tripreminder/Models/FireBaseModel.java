package iti.alex.intake40.team9.tripreminder.Models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Views.LoginView;

public class FireBaseModel {

    public static FireBaseModel sharedInstance = new FireBaseModel();
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private int count = 0;

    private FireBaseModel() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    public FirebaseUser getUser() {
        return auth.getCurrentUser();
    }

    public void addTrip(Trip trip) {
        myRef.child(getUser().getUid()).child("" + count++).setValue(trip);
    }

    public List<Trip> getTrips() {
        final List<Trip> list = new ArrayList<>();
        myRef.child(getUser().getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = (int) dataSnapshot.getChildrenCount();
                Log.i("ANE", "in the data changed");
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    list.add(dataSnapshot.child("" + i).getValue(Trip.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("ANE", databaseError.getMessage());
            }
        });
        return list;
    }
}
