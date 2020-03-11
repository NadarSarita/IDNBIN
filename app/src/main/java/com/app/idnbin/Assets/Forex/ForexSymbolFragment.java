package com.app.idnbin.Assets.Forex;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForexSymbolFragment extends Fragment {


    public ForexSymbolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forex_symbol, container, false);
    }

}
