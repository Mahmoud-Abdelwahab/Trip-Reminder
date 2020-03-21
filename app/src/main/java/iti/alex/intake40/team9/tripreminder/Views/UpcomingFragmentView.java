package iti.alex.intake40.team9.tripreminder.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Adapters.UpcomingRecyclarViewAdapter;
import iti.alex.intake40.team9.tripreminder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragmentView extends Fragment {
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager mgr;
    Trip[]trips={new Trip("Hello","When I became"),new Trip("Mahmoud","When I became"),new Trip("Ali","When I became"),new Trip("Hamdy","When I became Very Close"),new Trip("Tamer","When I became"),new Trip("Samir","When I became"),new Trip("Ahmed","When I became"),new Trip("Ali","When I became") };
    public UpcomingFragmentView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upcoming, container, false);
        rv=v.findViewById(R.id.Upcoming_RecyclarView);
        rv.setHasFixedSize(false);
        mgr=new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        adapter = new UpcomingRecyclarViewAdapter(trips,this.getContext());
        rv.setAdapter(adapter);



        return v;
    }
}
