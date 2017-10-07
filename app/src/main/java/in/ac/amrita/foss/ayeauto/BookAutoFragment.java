package in.ac.amrita.foss.ayeauto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class BookAutoFragment extends Fragment {

    EditText fromText, toText, dateText, timeText;
    Button searchButton;
    Switch newSwitch, shareSwitch;

    public BookAutoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_auto, container, false);

        fromText = (EditText) view.findViewById(R.id.fromText);
        toText = (EditText) view.findViewById(R.id.toText);
        dateText = (EditText) view.findViewById(R.id.dateText);
        timeText = (EditText) view.findViewById(R.id.timeText);

        newSwitch = (Switch) view.findViewById(R.id.newSwitch);
        shareSwitch = (Switch) view.findViewById(R.id.shareSwitch);

        searchButton = (Button) view.findViewById(R.id.searchButton);

        

        return view;
    }
}
