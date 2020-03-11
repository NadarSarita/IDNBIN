package com.app.idnbin.MainScreen.Chat;



import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.idnbin.MainScreen.Chat.Adapter.ChatAdapter;
import com.app.idnbin.MainScreen.Chat.Support.SupportChatActivity;
import com.app.idnbin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatFragment extends Fragment implements View.OnClickListener {
    ListView Chatview;
    CardView CV_EnglishChat;
    String[] groupchat = {"Digital Chat","Forex Chat","Stocks Chat","ETF Chat","Commodity Chat","Crypto Chat"};
    int[] countryFlags = {R.drawable.ic_etfchart,R.drawable.ic_forex_support,R.drawable.ic_stocks_support,R.drawable.ic_english_chat,R.drawable.ic_english,R.drawable.ic_crypto_support,};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        Chatview = view.findViewById(R.id.Chatview);
        CV_EnglishChat=view.findViewById(R.id.CV_EnglishChat);
        ChatAdapter myAdapter = new ChatAdapter(getContext(), groupchat, countryFlags);
        Chatview.setAdapter(myAdapter);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.layout_groupchat,R.id.TV_chat,groupchat);
//        Chatview.setAdapter(adapter);

        CV_EnglishChat.setOnClickListener(this);
        Chatview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent intent = new Intent(view.getContext(), DigitalchatActivity.class);
                    startActivityForResult(intent,0);
                }
                if (position==1){
                    Intent i = new Intent(view.getContext(),ForexChat.class);
                    startActivityForResult(i,1);
                }
                if (position==2){
                    Intent intent1 = new Intent(view.getContext(),StockActivity.class);
                    startActivityForResult(intent1,2);
                }
                if (position==3){
                    Intent intent2 = new Intent(view.getContext(),ETFChatActivity.class);
                    startActivityForResult(intent2,3);
                }
                if (position==4){
                    Intent intent3 = new Intent(view.getContext(),CommodChatActivity.class);
                    startActivityForResult(intent3,4);
                }

                if (position==5){
                    Intent intent4 = new Intent(view.getContext(),CryptochatActivity.class);
                    startActivityForResult(intent4,4);
                }
            }

        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CV_EnglishChat:
                startActivity(new Intent(getContext(), SupportChatActivity.class));
                break;
        }
    }
}
