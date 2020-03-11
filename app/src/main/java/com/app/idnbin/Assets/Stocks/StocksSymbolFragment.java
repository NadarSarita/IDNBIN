package com.app.idnbin.Assets.Stocks;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StocksSymbolFragment extends Fragment {


    public StocksSymbolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stocks_symbol, container, false);
    }

}
