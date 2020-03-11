package com.app.idnbin.Trade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.app.idnbin.R;

public class TradeActivity extends AppCompatActivity implements View.OnClickListener {

    CardView CvPrice,CvInvest,CvStopLoss,CvTakeProfit;
    Button BtnConfirm;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        CvPrice = findViewById(R.id.CvPrice);
        CvInvest = findViewById(R.id.CvInvest);
        CvStopLoss = findViewById(R.id.CvStopLoss);
        CvTakeProfit = findViewById(R.id.CvTakeProfit);
        BtnConfirm = findViewById(R.id.BtnConfirm);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Confirm Trade");

        CvPrice.setOnClickListener(this);
        CvInvest.setOnClickListener(this);
        CvStopLoss.setOnClickListener(this);
        CvTakeProfit.setOnClickListener(this);
        BtnConfirm.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.CvPrice:
                AlertDialog pricedialog = new AlertDialog.Builder(this).create();
                pricedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pricedialog.setView(getLayoutInflater().inflate(R.layout.layout_price_dialog, null));
                pricedialog.show();
                break;
            case R.id.CvInvest:
                AlertDialog investdialog = new AlertDialog.Builder(this).create();
                investdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                investdialog.setView(getLayoutInflater().inflate(R.layout.layout_invest_dialog, null));
                investdialog.show();
                break;
            case R.id.CvStopLoss:
                AlertDialog stoplossdialog = new AlertDialog.Builder(this).create();
                stoplossdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                stoplossdialog.setView(getLayoutInflater().inflate(R.layout.layout_stoploss_dialog, null));
                stoplossdialog.show();
                break;
            case R.id.CvTakeProfit:
                AlertDialog takeprofitdialog = new AlertDialog.Builder(this).create();
                takeprofitdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                takeprofitdialog.setView(getLayoutInflater().inflate(R.layout.layout_addprofit_dialog, null));
                takeprofitdialog.show();
                break;
            case R.id.BtnConfirm:
                finish();
                break;
        }
    }
}
