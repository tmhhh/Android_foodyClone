package hcmute.spkt.truongminhhoang.foodyclone;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.adapters.CartListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.adapters.FoodListAdapter;
import hcmute.spkt.truongminhhoang.foodyclone.classes.CartItem;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Food;
import hcmute.spkt.truongminhhoang.foodyclone.databinding.ActivityCartBinding;
import hcmute.spkt.truongminhhoang.foodyclone.services.CartService;


public class CartActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCartBinding binding;
    TextView totalTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.cartToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_cart);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        List<CartItem> cart = CartService.cart.getList();
        final ListView listView = (ListView) findViewById(R.id.cartLv);
        listView.setAdapter(new CartListAdapter(this, cart, () -> {
                totalTv.setText("Total: " + Integer.toString(CartService.getTotal()));
        }));

        totalTv = (TextView) findViewById(R.id.cartTotalTv);
        totalTv.setText("Total: " + Integer.toString(CartService.getTotal()));
    }

    @Override
    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_cart);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
        return false;
    }
}