package com.app.idnbin.MainScreen.Profile.History;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.idnbin.R;
import com.google.android.material.tabs.TabLayout;

public class HistoryActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    TabLayout tabl_history;
    ViewPager vp_history;
    TextView TvFilter;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tabl_history = findViewById(R.id.tabl_history);
        vp_history = findViewById(R.id.vp_history);
        TvFilter = findViewById(R.id.TvFilter);
        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("History");

        TvFilter.setOnClickListener(this);

        TabLayout.Tab open = tabl_history.newTab();
        open.setText("Trades");
        tabl_history.addTab(open);

        TabLayout.Tab pending = tabl_history.newTab();
        pending.setText("Orders");
        tabl_history.addTab(pending);

        HistoryPagerAdapter adapter = new HistoryPagerAdapter
                (getSupportFragmentManager(), tabl_history.getTabCount());
        vp_history.setAdapter(adapter);
        vp_history.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabl_history));
        tabl_history.setOnTabSelectedListener(this);

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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vp_history.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.TvFilter:
                startActivity(new Intent(this, FilterTradeActivity.class));
                break;
        }
    }
}
