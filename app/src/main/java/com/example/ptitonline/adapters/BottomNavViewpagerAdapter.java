package com.example.ptitonline.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ptitonline.fragments.BaithiFragment;
import com.example.ptitonline.fragments.LuyentapFragment;
import com.example.ptitonline.fragments.ProfileFragment;


public class BottomNavViewpagerAdapter extends FragmentStatePagerAdapter {
    public BottomNavViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LuyentapFragment();
            case 1:
                return new BaithiFragment();
            case 2:
                return new ProfileFragment();
            default:
                return new LuyentapFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
