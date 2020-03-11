package com.app.idnbin.MainScreen.Home;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.idnbin.MainScreen.Profile.Balance.BalanceActivity;
import com.app.idnbin.MainScreen.Profile.Deposit.DepositActivity;
import com.app.idnbin.R;
import com.app.idnbin.Assets.SymbolListActivity;
import com.app.idnbin.Trade.TradeActivity;
import com.app.idnbin.democall.BidData;
import com.app.idnbin.util.ApiCall;
import com.app.idnbin.util.ApiIterface;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.common.AccountPicker;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener, OnChartValueSelectedListener {
    private RelativeLayout RLInvest, RLLever, RLPrice, RLBuy, RLSell;
    ImageView IvAddSymbol;
    TextView TvBalance, TvDeposit;

    Handler handler = new Handler();
    int apiDelayed = 1*1000; //1 second=1000 milisecond, 5*1000=5seconds
    Runnable runnable;
    ArrayList<BidData> bidData;

    LineChart RTChart;
    float oldvalue, newvalue = 0;
    YAxis leftAxis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        IvAddSymbol = view.findViewById(R.id.IvAddSymbol);
        RLInvest = view.findViewById(R.id.RLInvest);
        RLLever = view.findViewById(R.id.RLLever);
        RLPrice = view.findViewById(R.id.RLPrice);
        RLBuy = view.findViewById(R.id.RLBuy);
        RLSell = view.findViewById(R.id.RLSell);
        TvBalance = view.findViewById(R.id.TvBalance);
        TvDeposit = view.findViewById(R.id.TvDeposit);

        IvAddSymbol.setOnClickListener(this);
        RLInvest.setOnClickListener(this);
        RLLever.setOnClickListener(this);
        RLPrice.setOnClickListener(this);
        RLBuy.setOnClickListener(this);
        RLSell.setOnClickListener(this);
        TvBalance.setOnClickListener(this);
        TvDeposit.setOnClickListener(this);

        RTChart = view.findViewById(R.id.RTChart);
        RTChart.setOnChartValueSelectedListener(this);
        // enable description text
        RTChart.getDescription().setEnabled(true);

        // enable touch gestures
        RTChart.setTouchEnabled(true);

        // enable scaling and dragging
        RTChart.setDragEnabled(true);
        RTChart.setScaleEnabled(true);
        RTChart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        RTChart.setPinchZoom(true);

        // set an alternative background color
        RTChart.setBackgroundColor(Color.BLACK);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        // add empty data
        RTChart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = RTChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.CIRCLE);
        l.setTextColor(Color.WHITE);

        /*XAxis xl = RTChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);*/

        leftAxis = RTChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);

        YAxis rightAxis = RTChart.getAxisRight();
        rightAxis.setEnabled(false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                //do your function;
                getDemoApi();
                handler.postDelayed(runnable, apiDelayed);
            }
        }, apiDelayed);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private  void getDemoApi(){
        ApiIterface apiService = ApiCall.getApiData().create(ApiIterface.class);
        Call<ArrayList<BidData>> call =apiService.getBidData();
        call.enqueue(new Callback<ArrayList<BidData>>() {
            @Override
            public void onResponse(Call<ArrayList<BidData>> call, Response<ArrayList<BidData>> response) {
                leftAxis.setAxisMaximum(oldvalue + 1);
                leftAxis.setAxisMinimum(oldvalue - 1);
                leftAxis.setDrawGridLines(true);
                bidData =response.body();
                oldvalue = bidData.get(0).getBid();
                if (oldvalue != newvalue){
                    Log.d("VALALAL",""+bidData.get(0).getBid());
                    addEntry(bidData.get(0).getBid());
                }
                newvalue = oldvalue;

            }

            @Override
            public void onFailure(Call<ArrayList<BidData>> call, Throwable t) {

            }
        });

    }

    void addEntry(float var){
        Log.d("MAPVALUE",""+var);
        LineData data = RTChart.getData();
        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }

            data.addEntry(new Entry(set.getEntryCount(), var), 0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            RTChart.notifyDataSetChanged();

            // limit the number of visible entries
            RTChart.setVisibleXRangeMaximum(5);
            // chart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            RTChart.moveViewToX(data.getEntryCount());

            // this automatically refreshes the chart (calls invalidate())
            // chart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
    }

    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Bid Price");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(1f);
        set.setCircleRadius(2f);
        set.setFillAlpha(10);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(20f);
        set.setDrawValues(false);
        return set;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TvDeposit:
                startActivity(new Intent(getContext(), DepositActivity.class));
                break;
            case R.id.TvBalance:
                startActivity(new Intent(getContext(), BalanceActivity.class));
                break;
            case R.id.RLInvest:
                AlertDialog investdialog = new AlertDialog.Builder(getContext()).create();
                investdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                investdialog.setView(getLayoutInflater().inflate(R.layout.layout_invest_dialog, null));
                investdialog.show();
                break;
            case R.id.RLLever:
                PopupMenu popuplever = new PopupMenu(getContext(), RLLever);
                popuplever.getMenuInflater().inflate(R.menu.menu_leverage, popuplever.getMenu());
                popuplever.show();
                break;
            case R.id.RLPrice:
                AlertDialog pricedialog = new AlertDialog.Builder(getContext()).create();
                pricedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pricedialog.setView(getLayoutInflater().inflate(R.layout.layout_price_dialog, null));
                pricedialog.show();
                break;
            case R.id.IvAddSymbol:
                startActivity(new Intent(getContext(), SymbolListActivity.class));
                break;

            case R.id.RLBuy:
                startActivity(new Intent(getContext(), TradeActivity.class));
                break;

            case R.id.RLSell:
                startActivity(new Intent(getContext(), TradeActivity.class));
                break;
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
    }

    @Override
    public void onNothingSelected() {

    }
}
