package hcmute.spkt.truongminhhoang.foodyclone;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.adapters.FoodListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.adapters.RestaurantListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Food;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Restaurant;
import hcmute.spkt.truongminhhoang.foodyclone.databinding.ActivityRestaurantDetailBinding;

public class RestaurantDetail extends AppCompatActivity {

    private ActivityRestaurantDetailBinding binding;
    private TextView restaurantName;
    private TextView restaurantAddress;

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

        List<Food> foods = getListData();
        final ListView listView = (ListView) findViewById(R.id.foodListLv);
        listView.setAdapter(new FoodListAdapter(this, foods));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getDrawbelByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName , "drawable", pkgName);
        return this.getResources().getDrawable(resID);
    }

    private List<Food> getListData() {
        List<Food> list = new ArrayList<Food>();

        list.add(new Food("phobo", "Pho Bo", "Food", 40000));
        list.add(new Food("tobokki", "Tobokki", "Food", 139000));
        list.add(new Food("thaihotpot", "Lau Thai", "Food", 500000));

        return list;
    }
}