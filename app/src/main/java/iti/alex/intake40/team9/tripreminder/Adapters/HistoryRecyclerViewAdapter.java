package iti.alex.intake40.team9.tripreminder.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import iti.alex.intake40.team9.tripreminder.Models.FireBaseModel;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.R;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>{

    private List<Trip> list;
    private Context context;
    private HistoryRecyclerViewAdapter adapter;

    public HistoryRecyclerViewAdapter(List<Trip> list, Context context) {
        this.list = list;
        this.context = context;
        this.adapter = this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_cell, parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTripName());
        holder.status.setText(list.get(position).getStatus());
        holder.start.setText(String.format("%s%s", context.getString(R.string.start_point), list.get(position).getStartPoint()));
        holder.end.setText(String.format("%s%s", context.getString(R.string.end_point), list.get(position).getEndPoint()));
        if (list.get(position).getRounded() != null)
            holder.round.setText(String.format("%s%s", context.getString(R.string.rounded_history), list.get(position).getRounded()));
        else
            holder.round.setText(String.format("%s%s", context.getString(R.string.rounded_history), "No"));
        holder.date.setText(String.format("%s%s", context.getString(R.string.date), list.get(position).getDate()));
        holder.time.setText(String.format("%s%s", context.getString(R.string.time), list.get(position).getTime()));
        //holder.image.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.start.getVisibility() == View.VISIBLE)
                    holder.start.setVisibility(View.GONE);
                else
                    holder.start.setVisibility(View.VISIBLE);

                if (holder.end.getVisibility() == View.VISIBLE)
                    holder.end.setVisibility(View.GONE);
                else
                    holder.end.setVisibility(View.VISIBLE);

                if (holder.round.getVisibility() == View.VISIBLE)
                    holder.round.setVisibility(View.GONE);
                else
                    holder.round.setVisibility(View.VISIBLE);

                if (holder.date.getVisibility() == View.VISIBLE)
                    holder.date.setVisibility(View.GONE);
                else
                    holder.date.setVisibility(View.VISIBLE);

                if (holder.time.getVisibility() == View.VISIBLE)
                    holder.time.setVisibility(View.GONE);
                else
                    holder.time.setVisibility(View.VISIBLE);

                if (holder.notes.getVisibility() == View.VISIBLE)
                    holder.notes.setVisibility(View.GONE);
                else
                    holder.notes.setVisibility(View.VISIBLE);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FireBaseModel.sharedInstance.deleteTrip(list.get(position));
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        holder.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> notes = list.get(position).getNotes();
                String [] notesArray = notes.toArray(new String[notes.size()]);
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.setTitle("Notes");

                dialog.setItems(notesArray, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, status, start, end, round, date, time;
        ImageButton delete, notes;
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.history_trip_title);
            this.status = itemView.findViewById(R.id.history_trip_status);
            this.start = itemView.findViewById(R.id.history_start_point);
            this.end = itemView.findViewById(R.id.history_end_point);
            this.round = itemView.findViewById(R.id.history_rounded);
            this.date = itemView.findViewById(R.id.history_date);
            this.time = itemView.findViewById(R.id.history_time);
            this.delete = itemView.findViewById(R.id.history_delete);
            this.notes = itemView.findViewById(R.id.history_notes);
            this.image = itemView.findViewById(R.id.history_trip_image);
        }
    }
}