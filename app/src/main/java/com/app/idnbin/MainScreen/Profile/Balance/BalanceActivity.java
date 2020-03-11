package com.app.idnbin.MainScreen.Profile.Balance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.idnbin.MainScreen.Profile.Deposit.DepositActivity;
import com.app.idnbin.R;
import com.app.idnbin.MainScreen.Profile.Withdraw.WithdrawActivity;

public class BalanceActivity extends AppCompatActivity implements View.OnClickListener {

    TextView TvAllTranscations;
    RelativeLayout RLWithdraw, RLDeposit;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        TvAllTranscations = findViewById(R.id.TvAllTranscations);
        RLWithdraw = findViewById(R.id.RLWithdraw);
        RLDeposit = findViewById(R.id.RLDeposit);
        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Balance");

        TvAllTranscations.setOnClickListener(this);
        RLWithdraw.setOnClickListener(this);
        RLDeposit.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TvAllTranscations:
                break;
            case R.id.RLWithdraw:
                startActivity(new Intent(this, WithdrawActivity.class));
                break;
            case R.id.RLDeposit:
                startActivity(new Intent(this, DepositActivity.class));
                break;
        }
    }
}
