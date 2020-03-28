package iti.alex.intake40.team9.tripreminder.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import iti.alex.intake40.team9.tripreminder.Contracts.IAddNoteFragment.AddNoteFragmentContract;
import iti.alex.intake40.team9.tripreminder.POJO.Trip;
import iti.alex.intake40.team9.tripreminder.Presenters.AddNoteFragmentPresenter;
import iti.alex.intake40.team9.tripreminder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragmentView extends Fragment implements AddNoteFragmentContract.IAddNoteFragmentView {

    private ArrayList<Trip> trips=new ArrayList<Trip>();
    ArrayAdapter adapter;
    EditText note;
    AddNoteFragmentPresenter addNoteFragmentPresenter;
    int position;

    public AddNoteFragmentView(int position) {
        this.position=position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addNoteFragmentPresenter=new AddNoteFragmentPresenter(this);
        addNoteFragmentPresenter.getTrips();
        View v= inflater.inflate(R.layout.fragment_add_note_view, container, false);
        final ListView lstview=v.findViewById(R.id.list);
        note =v.findViewById(R.id.Note);
        Button addNote=v.findViewById(R.id.AddNote);
        adapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1,trips.get(position).getNotes());
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trips.get(position).getNotes().add(String.valueOf(AddNoteFragmentView.this.note.getText()));
              adapter= new ArrayAdapter(AddNoteFragmentView.this.getContext(),android.R.layout.simple_list_item_1,trips.get(position).getNotes());
              lstview.setAdapter(adapter);
            }
        });
        lstview.setAdapter(adapter);
        lstview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(getContext());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        trips.get(position).getNotes().remove(position);
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
                return true;
            }
        });
        return v;
    }


    @Override
    public void onDataReceived(ArrayList<Trip> trips) {
        this.trips=trips;
    }
}
