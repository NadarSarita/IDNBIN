package com.app.idnbin.Assets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.idnbin.Assets.Commodities.CommodSymbolFragment;
import com.app.idnbin.Assets.Crypto.CryptoSymbolFragment;
import com.app.idnbin.Assets.Digital.DigitalSymbolFragment;
import com.app.idnbin.Assets.ETF.ETFSymbolFragment;
import com.app.idnbin.Assets.Forex.ForexSymbolFragment;
import com.app.idnbin.LoginRegister.FragmentRegistration;
import com.app.idnbin.R;
import com.app.idnbin.Assets.Stocks.StocksSymbolFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SymbolListActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    RecyclerView Rv_Featured;
    ArrayList<String> Data = new ArrayList<>();
    SymbolAdapter symbolAdapter;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbol_list);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        Rv_Featured = findViewById(R.id.Rv_Featured);
        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Assets");

        Data.add("EUD/ADS");
        Data.add("GBD/SUT");
        Data.add("XTR/MNT");

        Rv_Featured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        Rv_Featured.setItemAnimator(new DefaultItemAnimator());
        symbolAdapter = new SymbolAdapter(this,Data);
        Rv_Featured.setAdapter(symbolAdapter);

        SymbolListActivity.ViewPagerAdapter adapter = new SymbolListActivity.ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new FragmentRegistration();
            switch (position){
                case 0 : fragment = new DigitalSymbolFragment(); break;
                case 1 : fragment = new ForexSymbolFragment(); break;
                case 2 : fragment = new StocksSymbolFragment(); break;
                case 3 : fragment = new ETFSymbolFragment(); break;
                case 4 : fragment = new CommodSymbolFragment(); break;
                case 5 : fragment = new CryptoSymbolFragment(); break;
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Tabs";
            switch (position){
                case 0:title = "Digital"; break;
                case 1:title = "Forex"; break;
                case 2:title = "Stocks"; break;
                case 3:title = "ETF"; break;
                case 4:title = "Commod"; break;
                case 5:title = "Crypto"; break;
            }
            return title;
        }

        @Override
        public int getCount() {
            return 6;
        }
    }
}
