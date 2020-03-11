package com.app.idnbin.MainScreen.MarketAnalysis;


import android.content.Intent;
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
import android.widget.TextView;

import com.app.idnbin.MainScreen.MarketAnalysis.MovesAlerts.MovesAlertActivity;
import com.app.idnbin.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketAnalysisFragment extends Fragment implements View.OnClickListener {

    private TabLayout Tab_layout;
    private ViewPager view_pager;
    TextView TvMovesAlerts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_market_analysis, container, false);

        TvMovesAlerts = v.findViewById(R.id.TvMovesAlerts);
        Tab_layout = v.findViewById(R.id.Tab_layout);
        view_pager = v.findViewById(R.id.view_Pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        view_pager.setAdapter(adapter);
        Tab_layout.setupWithViewPager(view_pager);

        TvMovesAlerts.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), MovesAlertActivity.class));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new NewsFragment();
            switch (position){
                case 0 : fragment = new NewsFragment(); break;
                case 1 : fragment = new ForexFragment(); break;
//                case 2 : fragment = new EarningFragment(); break;
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Tabs";
            switch (position){
                case 0:title = "News"; break;
                case 1:title = "Forex"; break;
//                case 2:title = "Earning"; break;

            }
            return title;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
