package com.app.idnbin.MainScreen.Profile.History;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.MainScreen.Profile.History.TradesAdapter;
import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TradesFragment extends Fragment {


    RecyclerView recyclerView;
    TradesAdapter tradesAdapter;
    String[] a = {"abc","shf","fdf","sdsf"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trades, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_trades);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        tradesAdapter = new TradesAdapter(getActivity(), a);
        //recyclerView.setAdapter(tradesAdapter);
        return view;
    }

}
