package in.ac.amrita.foss.ayeauto;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.media.TimedText;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class BookAutoFragment extends Fragment {

    EditText fromText, toText;
    static EditText dateText, timeText;
    Button searchButton;
    Switch newSwitch, shareSwitch;

    static String response;

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

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPost();
            }
        });

        return view;
    }

    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.42.66:8000/ride/search/new/");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("location", fromText.getText().toString());

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

//                    Log.i("JSON_OUT", conn.get);
                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
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
