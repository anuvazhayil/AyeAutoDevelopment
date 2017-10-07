package in.ac.amrita.foss.ayeauto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CameraFragment extends Fragment {


    public CameraFragment() {
        // Required empty public constructor
    }

    EditText etEmail, etPassword;
    Button btnOK;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        etEmail = (EditText)view.findViewById(R.id.etEmail);
        etPassword = (EditText)view.findViewById(R.id.etPassword);
        btnOK = (Button)view.findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().equals("kosal") && etPassword.getText().toString().equals("kosal")){
                    Toast.makeText(CameraFragment.this.getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CameraFragment.this.getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}
