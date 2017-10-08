package in.ac.amrita.foss.ayeauto;

/**
 * Created by anu on 7/10/17.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ShareAutoFragment extends Fragment{

    EditText fromText, toText, seatLefts, luggages;
    static EditText dateText, timeText;
    Button searchButton;
    Switch newSwitch, shareSwitch;

    static String response;

    public ShareAutoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_share_auto, container, false);

        fromText = (EditText) view.findViewById(R.id.fromShare);
        toText = (EditText) view.findViewById(R.id.toShare);
        dateText = (EditText) view.findViewById(R.id.dateShare);
        timeText = (EditText) view.findViewById(R.id.timeShare);
        seatLefts = (EditText) view.findViewById(R.id.seatLefts);
        luggages = (EditText) view.findViewById(R.id.luggages);

        searchButton = (Button) view.findViewById(R.id.searchButton);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        timeText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });

//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        return view;
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        public DatePickerFragment() {
            // Required empty public constructor
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            dateText.setText(month + "/" + day + "/" + year);
        }

    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        public TimePickerFragment() {
            // Required empty public constructor
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, true);//Yes 24 hour time
        }

        public void populateSetDate(int hourOfDay, int minute) {
            timeText.setText(hourOfDay + ":" + minute);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            populateSetDate(hourOfDay, minute);
        }
    }

}
