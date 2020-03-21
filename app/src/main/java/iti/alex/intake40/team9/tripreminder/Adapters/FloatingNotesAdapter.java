package iti.alex.intake40.team9.tripreminder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import iti.alex.intake40.team9.tripreminder.R;

public class FloatingNotesAdapter extends ArrayAdapter<String> {
    Context context;
    String[] notes={"hello","me","Ali"};
    public FloatingNotesAdapter(@NonNull Context context, @NonNull String[] values) {

        super(context, R.layout.floating_item_note_cell,values);
        this.context=context;
        this.notes=values;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup listview) {
        View row = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.floating_item_note_cell,listview,false);
        TextView Note = row.findViewById(R.id.Note);
        Note.setText(notes[position]);
        return row;
    }
}
