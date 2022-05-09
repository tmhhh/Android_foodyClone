package hcmute.spkt.truongminhhoang.foodyclone;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.spkt.truongminhhoang.foodyclone.classes.Database;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Database db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new Database(getActivity(),"Foody.sqlite",null,1);
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_signup, container, false);
        Button btnSignUp= view.findViewById(R.id.btnSignup);
        TextView tvLogin=view.findViewById(R.id.tvLogin);
        EditText etUserName= view.findViewById(R.id.etUsername);
        EditText etFullName= view.findViewById(R.id.etFullName);
        EditText etPassword= view.findViewById(R.id.etPassword);
        EditText etAddress=view.findViewById(R.id.etAddress);
        EditText etPhoneNumber=view.findViewById(R.id.etPhonenumber);
        //
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.authorizeLayout,new LoginFragment());
                fragmentTransaction.commit();
            }
        });
        //
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isError=false;
                String userName = etUserName.getText().toString();
                String fullName = etFullName.getText().toString();
                String password = etPassword.getText().toString();
                String address=etAddress.getText().toString();
                String phonenumber=etAddress.getText().toString();
                //CHECK INPUT EMPTY
                if(userName.trim().isEmpty()){
                    etUserName.setError("This field is required !!!");
                    isError=true;
                }
                if(fullName.trim().isEmpty()){
                    etFullName.setError("This field is required !!!");
                    isError=true;
                }
                if(address.trim().isEmpty()){
                    etAddress.setError("This field is required !!!");
                    isError=true;
                }
                if(password.trim().isEmpty()){
                    etPassword.setError("This field is required !!!");
                    isError=true;
                }
                if(phonenumber.trim().isEmpty()){
                    etPhoneNumber.setError("This field is required !!!");
                    isError=true;
                }
                if(isError) return;

                String checkAccountExistQuery="SELECT * FROM User WHERE userName='"+userName+"'";
                Cursor cursor=db.GetData(checkAccountExistQuery);
                if(cursor.getCount()==0){
                    String registerAccountQuery="INSERT INTO User VALUES(null,'"+userName+"','"+fullName+"','"+password+"')";
                    db.QueryData(registerAccountQuery);
                    Toast.makeText(getActivity(), "Register successfully !!!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getActivity(), "This account has already existed !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}