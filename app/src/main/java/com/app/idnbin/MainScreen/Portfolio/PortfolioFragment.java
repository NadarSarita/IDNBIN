package com.app.idnbin.MainScreen.Portfolio;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.MainScreen.Portfolio.Closed.ClosedFragment;
import com.app.idnbin.MainScreen.Portfolio.Open.OpenFragment;
import com.app.idnbin.MainScreen.Portfolio.Pending.PendingFragment;
import com.app.idnbin.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class PortfolioFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_portfolio, container, false);

        tabLayout = v.findViewById(R.id.tl_portfolio);
        viewPager = v.findViewById(R.id.vp_fragment);

        TabLayout.Tab open = tabLayout.newTab();
        open.setText("Open");
        tabLayout.addTab(open);

        TabLayout.Tab pending = tabLayout.newTab();
        pending.setText("Pending");
        tabLayout.addTab(pending);

        TabLayout.Tab closed = tabLayout.newTab();
        closed.setText("Closed");
        tabLayout.addTab(closed);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new OpenFragment();
            switch (position){
                case 0 : fragment = new OpenFragment(); break;
                case 1 : fragment = new PendingFragment(); break;
                case 2 : fragment = new ClosedFragment(); break;
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Tabs";
            switch (position){
                case 0:title = "Open"; break;
                case 1:title = "Pending"; break;
                case 2:title = "Closed"; break;

            }
            return title;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
