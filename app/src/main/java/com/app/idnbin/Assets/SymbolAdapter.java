package com.app.idnbin.Assets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;

public class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.ViewHolder> {

    Context context;
    ArrayList<String> data;
    private static int currentPosition;

    public SymbolAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_symbol, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TV_SymbolName.setText(data.get(position));
        holder.LL_SymbolName.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_right);

            //toggling visibility
            holder.LL_SymbolName.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.LL_SymbolName.startAnimation(slideDown);
        }

        holder.IV_SymbolLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout LL_SymbolName;
        TextView TV_SymbolName;
        ImageView IV_SymbolLogo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            LL_SymbolName = itemView.findViewById(R.id.LL_SymbolName);
            TV_SymbolName = itemView.findViewById(R.id.TV_SymbolName);
            IV_SymbolLogo = itemView.findViewById(R.id.IV_SymbolLogo);
        }
    }
}
