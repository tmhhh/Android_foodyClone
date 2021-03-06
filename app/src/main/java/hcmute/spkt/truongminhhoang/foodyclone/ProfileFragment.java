package hcmute.spkt.truongminhhoang.foodyclone;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hcmute.spkt.truongminhhoang.foodyclone.classes.Restaurant;
import hcmute.spkt.truongminhhoang.foodyclone.classes.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    View btnLogout;
TextView tvUsername;
    TextView tvFullName;
    TextView tvPhone;
    TextView tvAddress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnLogout = view.findViewById(R.id.btnLogout);
        tvUsername=view.findViewById(R.id.tvUserName);
        tvFullName=view.findViewById(R.id.tvUserFullName);
        tvPhone=view.findViewById(R.id.tvUserPhone);
        tvAddress=view.findViewById(R.id.tvUserAddress);

        Intent intent = getActivity().getIntent();
        User currentUser =(User) intent.getSerializableExtra("userInfo");
        tvUsername.setText(currentUser.getUserName());
        tvFullName.setText(currentUser.getFullName());
        tvPhone.setText(currentUser.getPhone());
        tvAddress.setText(currentUser.getAddress());


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoveAccountFromSharePreferences();
                Intent myIntent = new Intent(getActivity(), UnauthorizedActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        return view;
    }

    private void RemoveAccountFromSharePreferences() {

        SharedPreferences myPrefs = getActivity().getSharedPreferences("authentication", MODE_PRIVATE);
        myPrefs.edit().remove("username").apply();
        myPrefs.edit().remove("password").apply();
    }
}