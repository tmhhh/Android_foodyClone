package hcmute.spkt.truongminhhoang.foodyclone;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.adapters.FoodListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.adapters.RestaurantListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Database;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Food;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Restaurant;
import hcmute.spkt.truongminhhoang.foodyclone.databinding.ActivityRestaurantDetailBinding;
import hcmute.spkt.truongminhhoang.foodyclone.util.Keyboard;

public class RestaurantDetail extends AppCompatActivity {

    private ActivityRestaurantDetailBinding binding;
    private TextView restaurantName;
    private TextView restaurantAddress;

    Database db;
    List<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRestaurantDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.restaurantDetailToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CollapsingToolbarLayout toolBarLayout = binding.restaurantDetailImage;
        toolBarLayout.setTitle(getTitle());

        restaurantName = (TextView) findViewById(R.id.restaurantName);
        restaurantAddress = (TextView) findViewById(R.id.restaurantAddress);

        Intent intent = getIntent();
        Restaurant restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        restaurantName.setText(restaurant.getName());
        restaurantAddress.setText(restaurant.getAddress());
        toolBarLayout.setBackground(this.getDrawbelByName(restaurant.getImage()));

        db = new Database(this, "Foody.sqlite", null, 1);

        createFoodTable();
        String sql = "SELECT * FROM Food WHERE restaurantId=" + restaurant.getId();
        if (getListData(sql).isEmpty()){
            insertFoodData();
        }
        foods = getListData(sql);
        final ListView listView = (ListView) findViewById(R.id.foodListLv);
        listView.setAdapter(new FoodListAdapter(this, foods));

        //SEARCH INPUT
        EditText et = (EditText) findViewById(R.id.restauranteDetailEtSearch);
        et.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    String query;
                    String value = et.getText().toString();
                    if (value.isEmpty()) {
                        query = sql;
                    } else {
                        query = "SELECT * FROM Food WHERE name LIKE '%" + value + "%' AND restaurantId=" + restaurant.getId();

                    }
                    Cursor cursor = db.GetData(query);
                    foods.clear();
                    foods = getListData(query);
                    listView.setAdapter(new FoodListAdapter(RestaurantDetail.this, foods));
                    Keyboard.hideKeyboard(RestaurantDetail.this);
                    return true;
                }
                return false;
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getDrawbelByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName , "drawable", pkgName);
        return this.getResources().getDrawable(resID);
    }

    private void createFoodTable() {
        String createFoodQuery = "CREATE TABLE IF NOT EXISTS Food(Id INTEGER PRIMARY KEY AUTOINCREMENT, image VARCHAR(200), name NVARCHAR(200), category VARCHAR(200), price INTEGER, restaurantId INTEGER)";
        db.QueryData("DROP TABLE IF EXISTS Food");
        db.QueryData(createFoodQuery);
    }

    private void insertFoodData() {
        List<String> listQuery = new ArrayList<String>();
        listQuery.add("INSERT INTO Food VALUES(null,'phobo','Phở Bò', 'Food', 40000, 1)");
        listQuery.add("INSERT INTO Food VALUES(null,'phobodb','Phở Bò Đặc biệt', 'Food', 80000, 1)");
        listQuery.add("INSERT INTO Food VALUES(null,'phobotc','Phở Bò Thập Cẩm', 'Food', 60000, 1)");
        listQuery.add("INSERT INTO Food VALUES(null,'phobokl','Phở Bò Khổng lồ', 'Food', 120000, 1)");
        listQuery.add("INSERT INTO Food VALUES(null,'tobokkihs','Tokbokki hải sản', 'Food', 59000, 2)");
        listQuery.add("INSERT INTO Food VALUES(null,'tobokki','Tokbokki truyền thống', 'Food', 39000, 2)");
        listQuery.add("INSERT INTO Food VALUES(null,'tobokkibf','Tokbokki buffet', 'Food', 139000, 2)");
        listQuery.add("INSERT INTO Food VALUES(null,'tobokkibo','Tokbokki Bò', 'Food', 59000, 2)");
        listQuery.add("INSERT INTO Food VALUES(null,'thaihotpot','Lẩu thái', 'Food', 150000, 3)");
        listQuery.add("INSERT INTO Food VALUES(null,'hotpotbo','Lẩu bò', 'Food', 150000, 3)");
        listQuery.add("INSERT INTO Food VALUES(null,'hotpotchay','Lẩu chay', 'Food', 100000, 3)");
        listQuery.add("INSERT INTO Food VALUES(null,'hotpotsc','Lẩu siêu cay', 'Food', 150000, 3)");
        listQuery.add("INSERT INTO Food VALUES(null,'phuclongts','Trà sữa Phúc Long', 'Drink', 50000, 4)");
        listQuery.add("INSERT INTO Food VALUES(null,'phuclongtx','Trà xanh Phúc Long', 'Drink', 60000, 4)");
        listQuery.add("INSERT INTO Food VALUES(null,'phuclongtd','Trà đào Phúc Long', 'Drink', 70000, 4)");
        listQuery.add("INSERT INTO Food VALUES(null,'phuclongtv','Trà vải Phúc Long', 'Drink', 70000, 4)");
        listQuery.add("INSERT INTO Food VALUES(null,'starbuckcf','Coffee', 'Drink', 100000, 5)");
        listQuery.add("INSERT INTO Food VALUES(null,'starbuckck','Cookie', 'Drink', 100000, 5)");
        listQuery.add("INSERT INTO Food VALUES(null,'starbucklm','Lemonade', 'Drink', 100000, 5)");
        listQuery.add("INSERT INTO Food VALUES(null,'vegecc','Rau xanh', 'Vege', 30000, 6)");
        listQuery.add("INSERT INTO Food VALUES(null,'vegelc','Trái cây', 'Vege', 50000, 6)");
        listQuery.add("INSERT INTO Food VALUES(null,'vegebc','Hàng nhập', 'Vege', 100000, 6)");
        listQuery.add("INSERT INTO Food VALUES(null,'cakedn','Donut Cake', 'Donut Cake', 200000, 7)");
        listQuery.add("INSERT INTO Food VALUES(null,'cakecr','Cream Cake', 'Cake', 200000, 7)");
        for (String query : listQuery) {
            db.QueryData(query);
        }
    }

    private List<Food> getListData(String query) {
        List<Food> list = new ArrayList<Food>();
        Cursor cursor = db.GetData(query);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String image = cursor.getString(1);
            String name = cursor.getString(2);
            String cate = cursor.getString(3);
            int price = Integer.parseInt(cursor.getString(4));
            list.add(new Food(image, name, cate, price));
        }
        return list;
    }

}