package iti.alex.intake40.team9.tripreminder.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iti.alex.intake40.team9.tripreminder.Models.FloatingItem;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.Views.AddNoteFragmentView;
import iti.alex.intake40.team9.tripreminder.Views.NavigationDrawerView;
import iti.alex.intake40.team9.tripreminder.Views.UpcomingFragmentView;

public class UpcomingRecyclarViewAdapter extends RecyclerView.Adapter<UpcomingRecyclarViewAdapter.MyViewHolder>  {
    public List<Trip> trips=new ArrayList<Trip>();
    Context context;
    MyViewHolder holder;
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(int position);
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tripName;
        private TextView startPoint;
        private TextView endPoint;
        private TextView date;
        private TextView time;
        private ImageView priorityImage;

        Button popupMenu;
        Button start;
        Button showNotes;

        public View layout;
        Context context;
        public MyViewHolder(final View v) {
            super(v);
            layout = v;
            tripName=v.findViewById(R.id.item_title);
            startPoint=v.findViewById(R.id.start_point_details);
            endPoint=v.findViewById(R.id.end_point_details);
            date=v.findViewById(R.id.date_details);
            time=v.findViewById(R.id.time_details);
            priorityImage=v.findViewById(R.id.high_priority_image);

            start=v.findViewById(R.id.Start);
            showNotes=v.findViewById(R.id.showNotes);
            popupMenu=(Button)v.findViewById(R.id.Popup_Menu);

        }
    }


    public UpcomingRecyclarViewAdapter(List<Trip> values, Context context) {

        this.trips = values;
        this.context=context;

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



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        this.holder=holder;
        holder.tripName.setText(trips.get(position).getTripName());
        holder.startPoint.setText(trips.get(position).getStartPoint());
        holder.endPoint.setText(trips.get(position).getEndPoint());
        holder.date.setText(trips.get(position).getDate());
        holder.time.setText(trips.get(position).getTime());
        holder.priorityImage.setImageResource(R.drawable.high_priority_img);
        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(UpcomingRecyclarViewAdapter.this.holder.start.getContext())) {

                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" +UpcomingRecyclarViewAdapter.this.holder.start.getContext().getPackageName()));
                    ((Activity)UpcomingRecyclarViewAdapter.this.holder.start.getContext()).startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
                } else {
                    UpcomingRecyclarViewAdapter.this.holder.start.getContext().startService(new Intent(UpcomingRecyclarViewAdapter.this.holder.start.getContext(), FloatingItem.class));
                    ((Activity)UpcomingRecyclarViewAdapter.this.holder.start.getContext()).finish();
                }
                trips.remove(position);
                UpcomingRecyclarViewAdapter.this.notifyDataSetChanged();
            }
        });


        holder.showNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpcomingRecyclarViewAdapter.this.holder.start.getContext());
                builder.setTitle("Notes");
                List<String> notes = trips.get(position).getNotes();
                //String[] notesArray = new String[notes.size()];

                String [] notesArray = notes.toArray(new String[notes.size()]);
                builder.setItems(notesArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // horse
                            case 1: // cow
                            case 2: // camel
                            case 3: // sheep
                            case 4: // goat
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        final PopupMenu popupMenu = new PopupMenu(UpcomingRecyclarViewAdapter.this.holder.popupMenu.getContext(),UpcomingRecyclarViewAdapter.this.holder.layout.findViewById(R.id.Popup_Menu));
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Add_Notes:
                        AddNoteFragmentView addNoteFragmentView= new AddNoteFragmentView(position);
                        ((FragmentActivity)UpcomingRecyclarViewAdapter.this.holder.popupMenu.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,addNoteFragmentView).addToBackStack( "tag" ).commit();
                        Toast.makeText(UpcomingRecyclarViewAdapter.this.holder.popupMenu.getContext(), "AddNotes",
                                Toast.LENGTH_LONG).show();

                        return true;
                    case R.id.Edit:
                        //Enter Edit Code Here;
                        Toast.makeText(UpcomingRecyclarViewAdapter.this.holder.popupMenu.getContext(), "Edit",
                                Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.Delete:
                        //delete from database
                        //removie from alarmlist
                        trips.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, trips.size());
                        return true;
                    case R.id.Cancel:
                        //Enter Cancel Code Here;
                        Toast.makeText(UpcomingRecyclarViewAdapter.this.holder.popupMenu.getContext(), "Cancel",
                                Toast.LENGTH_LONG).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setGravity(Gravity.LEFT);

        holder.popupMenu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View t) {

                popupMenu.show();

            }
        });


        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder adb=new AlertDialog.Builder(UpcomingRecyclarViewAdapter.this.holder.layout.getContext());
                adb.setTitle("Delete this trip?");
                adb.setMessage("Are you sure?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        trips.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, trips.size());
                    }});
                adb.show();

                return true;
            }
        });

    }

    public void updateTripList (ArrayList<String> notes,int posisiton)
    {
        Trip trip = trips.get(posisiton);
        trip.setNotes(notes);
        trips.set(posisiton,trip);

    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
