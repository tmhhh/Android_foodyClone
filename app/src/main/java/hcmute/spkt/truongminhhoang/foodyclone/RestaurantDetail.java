package hcmute.spkt.truongminhhoang.foodyclone;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

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

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.restaurantDetailImage;
        toolBarLayout.setTitle(getTitle());

        restaurantName = (TextView) findViewById(R.id.restaurantName);
        restaurantAddress = (TextView) findViewById(R.id.restaurantAddress);

        Intent intent = getIntent();
        Restaurant restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        restaurantName.setText(restaurant.getName());
        restaurantAddress.setText(restaurant.getAddress());
        toolBarLayout.setBackground(this.getDrawbelByName(restaurant.getImage()));

//        ((Button) findViewById(R.id.restaurantDetailBackBtn)).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getDrawbelByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName , "drawable", pkgName);
        return this.getResources().getDrawable(resID);
    }
}