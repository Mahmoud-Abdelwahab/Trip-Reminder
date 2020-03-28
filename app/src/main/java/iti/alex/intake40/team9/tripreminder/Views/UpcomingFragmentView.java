package iti.alex.intake40.team9.tripreminder.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import iti.alex.intake40.team9.tripreminder.Contracts.IUpcomingFragment.UpcomingFragmentContract;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Adapters.UpcomingRecyclarViewAdapter;
import iti.alex.intake40.team9.tripreminder.Presenters.UpcomingFragmentPresenter;
import iti.alex.intake40.team9.tripreminder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragmentView extends Fragment implements UpcomingFragmentContract.IUpcomingFragmentView {
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager mgr;
    ImageView emptyStateImage;
    TextView emptyStateText;
    private ArrayList<Trip> trips=new ArrayList<Trip>();
    private UpcomingFragmentPresenter upcomingFragmentPresenter;
    public UpcomingFragmentView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        upcomingFragmentPresenter=new UpcomingFragmentPresenter(this);
        upcomingFragmentPresenter.getTrips();
        View v = inflater.inflate(R.layout.fragment_upcoming, container, false);
        rv=v.findViewById(R.id.Upcoming_RecyclarView);
        emptyStateImage=v.findViewById(R.id.no_trips_image);
        emptyStateText=v.findViewById(R.id.no_trips_text);
        if(trips.size()!=0)
        {
            emptyStateImage.setVisibility(v.GONE);
            emptyStateText.setVisibility(v.GONE);
        }
        rv.setHasFixedSize(false);
        mgr=new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        adapter = new UpcomingRecyclarViewAdapter(trips,this.getContext());
        rv.setAdapter(adapter);




        return v;
    }

    @Override
    public void onDataReceived(ArrayList<Trip> trips) {
        this.trips=trips;
    }
}
