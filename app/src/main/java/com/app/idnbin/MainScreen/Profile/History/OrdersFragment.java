package com.app.idnbin.MainScreen.Profile.History;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.MainScreen.Profile.History.OrderAdapter;
import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    String[] a = {"abc","shf","fdf","sdsf"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_orders);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        orderAdapter = new OrderAdapter(getActivity(), a);
        //recyclerView.setAdapter(orderAdapter);
        return view;
    }

}
