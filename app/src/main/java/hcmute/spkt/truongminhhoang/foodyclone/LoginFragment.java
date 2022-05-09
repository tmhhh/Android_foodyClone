package hcmute.spkt.truongminhhoang.foodyclone;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.spkt.truongminhhoang.foodyclone.classes.Database;
import hcmute.spkt.truongminhhoang.foodyclone.classes.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    Database db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //
        EditText etUsername = view.findViewById(R.id.etUsername);
        EditText etPassword = view.findViewById(R.id.etPassword);
        Button btnLogin = (Button) view.findViewById(R.id.btnLogin);
        db = new Database(getActivity(), "Foody.sqlite", null, 1);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isError = false;
                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (userName.trim().isEmpty()) {
                    etUsername.setError("This field is required");
                    isError = true;
                }
                if (password.trim().isEmpty()) {
                    etPassword.setError("This field is required");
                    isError = true;
                }
                if (isError) return;
                String checkAccountExistQuery = "SELECT * FROM User WHERE userName='" + userName + "' AND password='" + password + "'";
                Cursor cursor = db.GetData(checkAccountExistQuery);
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        Log.d("test", String.valueOf(cursor.getInt(0)));
                        User loginUser = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                        Toast.makeText(getActivity(), "Login successfully !!!", Toast.LENGTH_SHORT).show();
                        SaveAccountToSharePreferences(userName, password);
                        Intent myIntent = new Intent(getActivity(), MainActivity.class);
                        myIntent.putExtra("userInfo", loginUser);
                        getActivity().startActivity(myIntent);
                    }

                } else {
                    Toast.makeText(getActivity(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //
        TextView tvSignUp = (TextView) view.findViewById(R.id.tvSignup);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.authorizeLayout, new SignupFragment());
                fragmentTransaction.commit();

            }
        });

        return view;
    }

    private void SaveAccountToSharePreferences(String username, String password) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("authentication", MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();

    }
}