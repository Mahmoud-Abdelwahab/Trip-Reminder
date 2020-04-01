package iti.alex.intake40.team9.tripreminder.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    private Bitmap bitmap;
    private boolean dataLoaded = false, imageLoaded = false;

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    private FireBaseModel() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        loadProfileImage();
        loadData();
    }

    private void loadData() {
        myRef.child(getUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    list.add(postSnapshot.getValue(Trip.class));
                    count = Integer.parseInt(postSnapshot.getKey());
                }
                Log.i("ANE", "data");
                dataLoaded = true;
                if (imageLoaded)
                    if (delegate != null) {
                        delegate.done();
                        delegate = null;
                        imageLoaded = false;
                        dataLoaded = false;
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
        if (trip.getFirebaseID() == null) {
            trip.setFirebaseID("" + ++count);
            myRef.child(getUser().getUid()).child("" + count).setValue(trip);
        }
        else {
            myRef.child(getUser().getUid()).child("" + trip.getFirebaseID()).setValue(trip);
        }
    }

    public List<Trip> getTrips() {
        return list;
    }

//    public void deleteAll() {
//        Log.i("ANE", "deleteAll");
//        myRef.child(getUser().getUid()).removeValue();
//        Log.i("ANE", "deleteAll2");
//    }

    public void deleteTrip(Trip trip) {
        myRef.child(getUser().getUid()).child("" + trip.getFirebaseID()).removeValue();
    }

    public void signOut() { auth.signOut(); }

    public void loadProfileImage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child(auth.getUid() + "/profile");
        //final Bitmap[] bitmap = new Bitmap[1];
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageLoaded = true;
                if (dataLoaded)
                    if (delegate != null) {
                        delegate.done();
                        delegate = null;
                        imageLoaded = false;
                        dataLoaded = false;
                    }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                imageLoaded = true;
                if (dataLoaded)
                    if (delegate != null) {
                        delegate.done();
                        delegate = null;
                        imageLoaded = false;
                        dataLoaded = false;
                    }
                bitmap = null;
            }
        });

    }

    public Bitmap getProfileImage() {
        return bitmap;
    }
}
