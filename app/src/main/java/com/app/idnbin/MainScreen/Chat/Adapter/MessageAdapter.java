package com.app.idnbin.MainScreen.Chat.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.app.idnbin.MainScreen.Chat.Model.AllMethods;
import com.app.idnbin.MainScreen.Chat.Model.Message;
import com.app.idnbin.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    Context context;
    List<Message> messages;
    DatabaseReference messagedb;

    public MessageAdapter(Context context, List<Message>messages, DatabaseReference messagedb){
        this.context = context;
        this.messagedb = messagedb;
        this.messages = messages;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_forex,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(messages.get(position).getMessage());

        if (messages.get(position).getEmail().equals(AllMethods.name)){
            holder.username.setText("You :"+messages.get(position).getMessage());

            Glide.with(context).load(messages.get(position).getImageURL()).into(holder.profile_image);
        }else {
            holder.username.setText(messages.get(position).getEmail() +":"+ messages.get(position).getMessage());
            Glide.with(context).load(messages.get(position).getImageURL()).into(holder.profile_image);
        }
        Log.d("","datadatada"+messages.get(position).getImageURL());


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        ImageView profile_image;
        CardView card_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }

}
