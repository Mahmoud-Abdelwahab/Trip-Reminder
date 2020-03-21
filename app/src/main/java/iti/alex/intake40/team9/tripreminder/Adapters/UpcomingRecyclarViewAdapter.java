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
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import iti.alex.intake40.team9.tripreminder.Models.FloatingItem;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.R;
import iti.alex.intake40.team9.tripreminder.Views.AddNoteFragmentView;
import iti.alex.intake40.team9.tripreminder.Views.NavigationDrawerView;
import iti.alex.intake40.team9.tripreminder.Views.UpcomingFragmentView;

public class UpcomingRecyclarViewAdapter extends RecyclerView.Adapter<UpcomingRecyclarViewAdapter.MyViewHolder>  {
    private Trip[] trips;
    Context context;




    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
        private TextView title;
        private TextView startPoint;
        private TextView endPoint;
        private TextView date;
        private TextView time;

        Button popupMenu;
        Button start;
        Button showNotes;

        public View layout;
        Context context;
        public MyViewHolder(final View v) {
            super(v);
            layout = v;
            title=v.findViewById(R.id.item_title);
            startPoint=v.findViewById(R.id.start_point_details);
            endPoint=v.findViewById(R.id.end_point_details);
            date=v.findViewById(R.id.date_details);
            time=v.findViewById(R.id.time_details);

            start=v.findViewById(R.id.Start);
            showNotes=v.findViewById(R.id.showNotes);
            popupMenu=(Button)v.findViewById(R.id.Popup_Menu);


            //Buttons listeners

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(start.getContext())) {


                        //If the draw over permission is not available open the settings screen
                        //to grant the permission.
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + start.getContext().getPackageName()));
                        ((Activity)start.getContext()).startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
                    } else {
                        start.getContext().startService(new Intent(start.getContext(), FloatingItem.class));
                        ((Activity)start.getContext()).finish();
                    }
                }
            });

            showNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(showNotes.getContext());
                    builder.setTitle("Notes");
                    String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
                    builder.setItems(animals, new DialogInterface.OnClickListener() {
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

            popupMenu.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View t) {
                    final PopupMenu popupMenu = new PopupMenu(MyViewHolder.this.popupMenu.getContext(),v.findViewById(R.id.Popup_Menu));
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.Add_Notes:
                                    ((FragmentActivity)MyViewHolder.this.popupMenu.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddNoteFragmentView()).addToBackStack( "tag" ).commit();
                                    Toast.makeText(MyViewHolder.this.popupMenu.getContext(), "AddNotes",
                                            Toast.LENGTH_LONG).show();
                                    return true;
                                case R.id.Edit:
                                    //Enter Edit Code Here;
                                    Toast.makeText(MyViewHolder.this.popupMenu.getContext(), "Edit",
                                            Toast.LENGTH_LONG).show();
                                    return true;
                                case R.id.Delete:
                                    //Enter Delete Code Here;
                                    Toast.makeText(MyViewHolder.this.popupMenu.getContext(), "Delete",
                                            Toast.LENGTH_LONG).show();
                                    return true;
                                case R.id.Cancel:
                                    //Enter Cancel Code Here;
                                    Toast.makeText(MyViewHolder.this.popupMenu.getContext(), "Cancel",
                                            Toast.LENGTH_LONG).show();
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.setGravity(Gravity.LEFT);
                    popupMenu.show();

                }
            });
        }
    }


    public UpcomingRecyclarViewAdapter(Trip[] values,Context context) {

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



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(trips[position].getTripName());
        holder.startPoint.setText(trips[position].getStatus());
        holder.endPoint.setText(trips[position].getStatus());
        holder.date.setText(trips[position].getStatus());
        holder.time.setText(trips[position].getStatus());
    }


    @Override
    public int getItemCount() {
        return trips.length;
    }
}
