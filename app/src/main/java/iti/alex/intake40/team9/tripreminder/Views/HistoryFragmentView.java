package iti.alex.intake40.team9.tripreminder.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import iti.alex.intake40.team9.tripreminder.Adapters.HistoryRecyclerViewAdapter;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Presenters.HistoryDelegate;
import iti.alex.intake40.team9.tripreminder.Presenters.HistoryPresenter;
import iti.alex.intake40.team9.tripreminder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragmentView extends Fragment implements HistoryDelegate {

    private ImageView imageView;
    private TextView textView;
    private RecyclerView recyclerView;

    public HistoryFragmentView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_, container, false);
        imageView = view.findViewById(R.id.historyImage);
        textView = view.findViewById(R.id.historyText);
        recyclerView = view.findViewById(R.id.historyRecycler);
        HistoryPresenter presenter = new HistoryPresenter(this, this);
        return view;
    }

    @Override
    public void showImage() {
        imageView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void fillRecycler(List<Trip> list) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        RecyclerView.Adapter adapter = new HistoryRecyclerViewAdapter(list, this.getContext());
        recyclerView.setAdapter(adapter);
    }


}
