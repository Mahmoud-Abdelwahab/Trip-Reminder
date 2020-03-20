package iti.alex.intake40.team9.tripreminder.Presenters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.R;

public class UpcomingRecyclarViewAdapter extends RecyclerView.Adapter<UpcomingRecyclarViewAdapter.MyViewHolder> {
    private Trip[] trips;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView details;


        public CardView cardView;
        public View layout;
        public MyViewHolder(View v) {
            super(v);
            layout = v;
            title=v.findViewById(R.id.item_title);
            details=v.findViewById(R.id.start_point_details);
            cardView =v.findViewById(R.id.card_view);
        }
    }


    public UpcomingRecyclarViewAdapter(Trip[] values) {

        this.trips = values;

    }

    @Override
    public UpcomingRecyclarViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                       int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.upcoming_cell,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(trips[position].getTripName());
        holder.details.setText(trips[position].getStatus());
    }


    @Override
    public int getItemCount() {
        return trips.length;
    }
}
