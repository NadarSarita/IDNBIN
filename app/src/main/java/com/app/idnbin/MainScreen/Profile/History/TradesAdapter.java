package com.app.idnbin.MainScreen.Profile.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;


public class TradesAdapter extends RecyclerView.Adapter<TradesAdapter.ItemViewHolder> {

    Context context;
    String a[];

    public TradesAdapter(Context context, String[] a) {
        this.context = context;
        this.a = a;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trades_layout, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return a.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_st,tv_dgl,tv_cncy,tv_prnt;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_st =   itemView.findViewById(R.id.tv_st);
            tv_dgl =  itemView.findViewById(R.id.tv_dgl);
            tv_cncy = itemView.findViewById(R.id.tv_cncy);
            tv_prnt = itemView.findViewById(R.id.tv_prnt);
        }
    }
}
