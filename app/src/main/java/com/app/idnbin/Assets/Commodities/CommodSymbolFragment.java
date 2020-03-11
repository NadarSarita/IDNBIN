package com.app.idnbin.Assets.Commodities;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommodSymbolFragment extends Fragment {


    public CommodSymbolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_commod_symbol, container, false);
    }

}
