package hcmute.spkt.truongminhhoang.foodyclone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.adapters.RestaurantListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Restaurant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        List<Restaurant> restaurants = getListData();
        final ListView listView = (ListView) view.findViewById(R.id.restaurantsLv);
        listView.setAdapter(new RestaurantListAdapter(getActivity(), restaurants));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Restaurant restaurant = (Restaurant) listView.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Selected :" + " " + restaurant.toString(), Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(getActivity(), RestaurantDetail.class);
                myIntent.putExtra("restaurant", restaurant);
                getActivity().startActivity(myIntent);
            }
        });

            return view;
    }
    private List<Restaurant> getListData() {
        List<Restaurant> list = new ArrayList<Restaurant>();

        list.add(new Restaurant("res1" ,"pho24", "Pho 24", "Food", "40.000", "Nhiều chi nhánh"));
        list.add(new Restaurant("res2" ,"dookki", "Dookki", "Food", "139.000", "Nhiều chi nhánh"));
        list.add(new Restaurant("res3" ,"hadilao", "Hadilao", "Food", "500.000", "Nhiều chi nhánh"));

        return list;
    }
}