package hcmute.spkt.truongminhhoang.foodyclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HistoryPageAdapter extends FragmentStatePagerAdapter {
    public HistoryPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public HistoryPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ONGOING";
            case 1:
                return "HISTORY";
            default:
                return  "HISTORY";
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        switch (position) {
//            default:
//                return new HistoryFragment();
//        }

        return new HistoryFragment();

    }

    @Override
    public int getCount() {
        return 2;
    }
}
