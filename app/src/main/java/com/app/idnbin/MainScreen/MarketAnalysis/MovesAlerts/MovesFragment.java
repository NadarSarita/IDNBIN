package com.app.idnbin.MainScreen.MarketAnalysis.MovesAlerts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovesFragment extends Fragment {

    public MovesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moves, container, false);
    }
}