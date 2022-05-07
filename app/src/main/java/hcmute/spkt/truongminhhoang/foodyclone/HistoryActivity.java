package hcmute.spkt.truongminhhoang.foodyclone;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.spkt.truongminhhoang.foodyclone.databinding.ActivityHistoryBinding;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TabLayout tabLayout = findViewById(R.id.historyTab);
        ViewPager viewPager = findViewById(R.id.HistoryViewPager);

        HistoryPageAdapter viewPageAdapter = new HistoryPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}