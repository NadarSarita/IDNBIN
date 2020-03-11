package com.app.idnbin.MainScreen.Profile.Deposit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.idnbin.R;

public class SkrillActivity extends AppCompatActivity implements View.OnClickListener {

    TextView TvAmt1,TvAmt2,TvAmt3,TvAmt4,TvAmt5;
    Button BtnContinue;
    Spinner SpAmount;
    String[] Amount = { "$ USD", "£ GBP", "€ EUR", "P RUB"};
    EditText ETAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skrill);

        TvAmt1 = findViewById(R.id.TvAmt1);
        TvAmt2 = findViewById(R.id.TvAmt2);
        TvAmt3 = findViewById(R.id.TvAmt3);
        TvAmt4 = findViewById(R.id.TvAmt4);
        TvAmt5 = findViewById(R.id.TvAmt5);
        ETAmount = findViewById(R.id.ETAmount);
        BtnContinue = findViewById(R.id.BtnContinue);

        SpAmount = findViewById(R.id.SpAmount);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Amount);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpAmount.setAdapter(aa);

        TvAmt1.setOnClickListener(this);
        TvAmt2.setOnClickListener(this);
        TvAmt3.setOnClickListener(this);
        TvAmt4.setOnClickListener(this);
        TvAmt5.setOnClickListener(this);
        BtnContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.TvAmt1:
                ETAmount.setText("250");
                BtnContinue.setText("DEPOSIT $"+ETAmount.getText().toString());
                break;
            case R.id.TvAmt2:
                ETAmount.setText("500");
                BtnContinue.setText("DEPOSIT $"+ETAmount.getText().toString());
                break;
            case R.id.TvAmt3:
                ETAmount.setText("1000");
                BtnContinue.setText("DEPOSIT $"+ETAmount.getText().toString());
                break;
            case R.id.TvAmt4:
                ETAmount.setText("3000");
                BtnContinue.setText("DEPOSIT $"+ETAmount.getText().toString());
                break;
            case R.id.TvAmt5:
                ETAmount.setText("5000");
                BtnContinue.setText("DEPOSIT $"+ETAmount.getText().toString());
                break;
        }
    }
}
