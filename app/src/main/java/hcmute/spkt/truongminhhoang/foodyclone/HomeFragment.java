package hcmute.spkt.truongminhhoang.foodyclone;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.adapters.RestaurantListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Database;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Restaurant;
import hcmute.spkt.truongminhhoang.foodyclone.util.Keyboard;

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

    ListView listView;
    List<Restaurant> restaurants;
    Database db;
    EditText et;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //INITIALIZE VARIABLE
        listView = (ListView) view.findViewById(R.id.restaurantsLv);
        et = (EditText) view.findViewById(R.id.etSearch);
        db = new Database(getActivity(), "Foody.sqlite", null, 1);
        createRestaurantTable();
        restaurants = getListData("SELECT * FROM Restaurant");
        mapListResToView();
        setListViewHeight();
        // EVENT
        //SEARCH INPUT
        et.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    String query;
                    String value = et.getText().toString();
                    if (value.isEmpty()) {
                        query = "SELECT * FROM Restaurant";
                    } else {
                        query = "SELECT * FROM Restaurant WHERE name LIKE '%" + value + "%'";
                    }
                    Cursor cursor = db.GetData(query);
                    restaurants.clear();
                    restaurants = getListData(query);
                    mapListResToView();
                    Keyboard.hideKeyboard(getActivity());
                    return true;
                }
                return false;
            }
        });

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

    private void createRestaurantTable() {
        String createRestaurantQuery = "CREATE TABLE IF NOT EXISTS Restaurant(Id INTEGER PRIMARY KEY AUTOINCREMENT, image VARCHAR(200), name NVARCHAR(200), category VARCHAR(200), avgPrice VARCHAR(200), address NVARCHAR(200))";
        db.QueryData(createRestaurantQuery);
    }

    private void insertRestaurantData() {
//        String query = "INSERT INTO Restaurant VALUES(null,'pho24','Phở Ông Hùng', 'Food', '40.000', '66 Ngô Đức Kế, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh')";
//        String query = "INSERT INTO Restaurant VALUES(null,'dookki','Dokki', 'Food', '130.000', '11 Sư Vạn Hạnh, Phường 12, Quận 10, Thành phố Hồ Chí Minh')";
        String query = "INSERT INTO Restaurant VALUES(null,'hadilao','Hadilao', 'Food', '500.000', 'Thành phố Hồ Chí Minh, Quận 1, Lê Thánh Tôn, L3-8')";

        db.QueryData(query);
    }


    private void setListViewHeight() {
        int itemHeight = 400;
        listView.getLayoutParams().height = restaurants.size() * itemHeight;

    }

    private List<Restaurant> getListData(String query) {
        List<Restaurant> list = new ArrayList<Restaurant>();
        Cursor cursor = db.GetData(query);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String image = cursor.getString(1);
            String name = cursor.getString(2);
            String cate = cursor.getString(3);
            String avgPrice = cursor.getString(4);
            String address = cursor.getString(5);
            list.add(new Restaurant(id, image, name, cate, avgPrice, address));
        }
        return list;
    }

    private void mapListResToView() {
        listView.setAdapter(new RestaurantListAdapter(getActivity(), restaurants));

    }


}