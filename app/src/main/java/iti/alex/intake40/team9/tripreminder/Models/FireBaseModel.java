package iti.alex.intake40.team9.tripreminder.Models;

import android.util.Log;

import androidx.annotation.NonNull;

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

public class FireBaseModel {

    public static FireBaseModel sharedInstance = new FireBaseModel();
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static int count = 0;
    private static List<Trip> list = new ArrayList<>();
    private Delegate delegate;

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    private FireBaseModel() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child(getUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    list.add(postSnapshot.getValue(Trip.class));
                    count = Integer.parseInt(postSnapshot.getKey());
                }
                if (delegate != null) {
                    delegate.done();
                    delegate = null;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("ANE", databaseError.getMessage());
            }
        });
    }

    public String getUserEmail() { return auth.getCurrentUser().getEmail(); }

    public FirebaseUser getUser() {
        return auth.getCurrentUser();
    }

    public void addTrip(Trip trip) {
        trip.setFirebaseID("" + ++count);
        myRef.child(getUser().getUid()).child("" + count).setValue(trip);
    }

    public List<Trip> getTrips() {
        return list;
    }

    public void deleteTrip(Trip trip) {
        myRef.child(getUser().getUid()).child("" + trip.getFirebaseID()).removeValue();
    }

    public void updateTrip(Trip trip) {
        myRef.child(getUser().getUid()).child("" + trip.getFirebaseID()).setValue(trip);
    }

    public void signOut() { auth.signOut(); }
}
