package iti.alex.intake40.team9.tripreminder.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iti.alex.intake40.team9.tripreminder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragmentView extends Fragment {

    public UpcomingFragmentView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }
}