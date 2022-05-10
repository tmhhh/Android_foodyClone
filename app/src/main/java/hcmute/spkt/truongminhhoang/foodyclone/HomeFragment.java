package hcmute.spkt.truongminhhoang.foodyclone;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

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

import hcmute.spkt.truongminhhoang.foodyclone.adapters.CategoryAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.adapters.RestaurantListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Category;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Database;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Restaurant;
import hcmute.spkt.truongminhhoang.foodyclone.classes.User;
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
    List<Category> listCate;
    Database db;
    EditText et;
    TextView tvUserAddress;

    //
    View cateAll;
    View cateFood;
    View cateDrink;
    View cateVege;
    View cateCake;
    String chosenCate = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //INITIALIZE VARIABLE
        et = (EditText) view.findViewById(R.id.etSearch);
        tvUserAddress = view.findViewById(R.id.userAddress);
        db = new Database(getActivity(), "Foody.sqlite", null, 1);
        Intent intent = getActivity().getIntent();
        User currentUser = (User) intent.getSerializableExtra("userInfo");
        tvUserAddress.setText("To " + currentUser.getAddress());
        listView = (ListView) view.findViewById(R.id.restaurantsLv);
//        insertRestaurantData();
        restaurants = getListData("SELECT * FROM Restaurant");
        listCate = getCategoryData();
        createRestaurantTable();
        mapListResToView();
//        mapListCateToView();
        setListViewHeight();


        //CATEGORY
        cateAll = view.findViewById(R.id.cateAll);
        cateAll.setBackground(getContext().getResources().getDrawable(R.drawable.boxshadow));
        cateAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherAlphaValue();
//                cateAll.setAlpha(0.4f);
                cateAll.setBackground(getContext().getResources().getDrawable(R.drawable.boxshadow));
                chosenCate = "All";
                String value = et.getText().toString();
                String query = "SELECT * FROM Restaurant WHERE name LIKE '%" + value + "%'";
                Cursor cursor = db.GetData(query);
                restaurants.clear();
                restaurants = getListData(query);
                mapListResToView();
            }
        });
        cateFood = view.findViewById(R.id.cateFood);
        cateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherAlphaValue();
                cateFood.setBackground(getContext().getResources().getDrawable(R.drawable.boxshadow));

                chosenCate = "Food";
                String value = et.getText().toString();
                String query = "SELECT * FROM Restaurant WHERE name LIKE '%" + value + "%' AND category='" + chosenCate + "'";
                Cursor cursor = db.GetData(query);
                restaurants.clear();
                restaurants = getListData(query);
                mapListResToView();

            }
        });
        cateDrink = view.findViewById(R.id.cateDrink);
        cateDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherAlphaValue();
                cateDrink.setBackground(getContext().getResources().getDrawable(R.drawable.boxshadow));

                chosenCate = "Drink";
                String value = et.getText().toString();
                String query = "SELECT * FROM Restaurant WHERE name LIKE '%" + value + "%' AND category='" + chosenCate + "'";
                Cursor cursor = db.GetData(query);
                restaurants.clear();
                restaurants = getListData(query);
                mapListResToView();
            }
        });
        cateVege = view.findViewById(R.id.cateVege);
        cateVege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherAlphaValue();
                cateVege.setBackground(getContext().getResources().getDrawable(R.drawable.boxshadow));

                chosenCate = "Vege";
                String value = et.getText().toString();
                String query = "SELECT * FROM Restaurant WHERE name LIKE '%" + value + "%' AND category='" + chosenCate + "'";
                Cursor cursor = db.GetData(query);
                restaurants.clear();
                restaurants = getListData(query);
                mapListResToView();
            }
        });
        cateCake = view.findViewById(R.id.cateCake);
        cateCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherAlphaValue();
                cateCake.setBackground(getContext().getResources().getDrawable(R.drawable.boxshadow));

                chosenCate = "Cake";
                String value = et.getText().toString();
                String query = "SELECT * FROM Restaurant WHERE name LIKE '%" + value + "%' AND category='" + chosenCate + "'";
                Cursor cursor = db.GetData(query);
                restaurants.clear();
                restaurants = getListData(query);
                mapListResToView();
            }
        });

        // EVENT
        //SEARCH INPUT
        et.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    String query;
                    String value = et.getText().toString();
                    if (value.isEmpty()) {
                        query = "SELECT * FROM Restaurant" + ((!chosenCate.isEmpty() && !chosenCate.equals("All")) ? " WHERE category='" + chosenCate + "'" : "");
                    } else {
                        query = "SELECT * FROM Restaurant WHERE name LIKE '%" + value + "%'" + ((!chosenCate.isEmpty() && !chosenCate.equals("All")) ? "AND category='" + chosenCate + "'" : "");

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
        List<String> listQuery = new ArrayList<String>();
        listQuery.add("INSERT INTO Restaurant VALUES(null,'pho24','Phở Ông Hùng', 'Food', '40.000', '66 Ngô Đức Kế, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh')");
        listQuery.add("INSERT INTO Restaurant VALUES(null,'dookki','Dokki', 'Food', '130.000', '11 Sư Vạn Hạnh, Phường 12, Quận 10, Thành phố Hồ Chí Minh')");
        listQuery.add("INSERT INTO Restaurant VALUES(null,'hadilao','Hadilao', 'Food', '500.000', 'Thành phố Hồ Chí Minh, Quận 1, Lê Thánh Tôn, L3-8')");
        listQuery.add("INSERT INTO Restaurant VALUES(null,'phuclong','Phúc Long', 'Drink', '50.000', '42 Ngô Đức Kế, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh')");
        listQuery.add("INSERT INTO Restaurant VALUES(null,'starbuck','StarBuck', 'Drink', '100.000', '99 Nguyễn Huệ, P, Quận 1, Thành phố Hồ Chí Minh')");
        listQuery.add("INSERT INTO Restaurant VALUES(null,'vege','Rau Ơi Vegetarian Chay', 'Vege', '30.000', ' 55/15 Lê Thị Hồng Gấm')");
        listQuery.add("INSERT INTO Restaurant VALUES(null,'cake','Annie's Cake', 'Cake', '200.000', ' 55/15 Lê Thị Hồng Gấm')");
        for (String query : listQuery) {
            db.QueryData("INSERT INTO Restaurant VALUES(null,'cake','Annie's Cake', 'Cake', '200.000', ' 55/15 Lê Thị Hồng Gấm')");
        }
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

    private void mapListCateToView() {
//        lvCate.setAdapter(new CategoryAdapter(getActivity(),listCate));
    }

    private List<Category> getCategoryData() {
        List<Category> addedListCate = new ArrayList<Category>();
        addedListCate.add(new Category("All", "all_icon"));
        addedListCate.add(new Category("Food", "food_icon"));
        addedListCate.add(new Category("Drink", "drink_icon"));
        addedListCate.add(new Category("Vege", "vege_icon"));
        addedListCate.add(new Category("Cake", "cake_icon"));
        return addedListCate;
    }

    private void resetOtherAlphaValue() {
        cateAll.setBackground(null);
        cateFood.setBackground(null);
        cateDrink.setBackground(null);
        cateVege.setBackground(null);
        cateCake.setBackground(null);

    }


}