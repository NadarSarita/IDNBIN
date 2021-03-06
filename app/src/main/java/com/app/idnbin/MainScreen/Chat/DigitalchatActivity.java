package com.app.idnbin.MainScreen.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.app.idnbin.LoginRegister.UserDetails;
import com.app.idnbin.MainScreen.Chat.Adapter.MessageAdapter;
import com.app.idnbin.MainScreen.Chat.Model.AllMethods;
import com.app.idnbin.MainScreen.Chat.Model.Message;
import com.app.idnbin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DigitalchatActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    MessageAdapter messageAdapter;
    UserDetails user;
    List<Message> englishchats;
    RecyclerView recyclerView;
    ImageButton btn_send;
    EditText text_send;
    FirebaseUser currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = findViewById(R.id.message_view);
        btn_send = findViewById(R.id.btn_send);
        init();
        getMessage();
    }
    private void init(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = new UserDetails();

        btn_send.setOnClickListener(this);

        text_send = findViewById(R.id.text_send);
        englishchats = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(text_send.getText().toString())){
            Message message = new Message(text_send.getText().toString(),currentuser.getEmail(),currentuser.getPhotoUrl().toString());
            Log.d("","Datddddddddddddd"+currentuser.getPhotoUrl().toString());
            text_send.setText("");
            reference.child("").push().setValue(message);
        }
    }

    private void getMessage(){
        currentuser = firebaseAuth.getCurrentUser();
        Log.d("VALALALA",""+currentuser.getPhotoUrl());
        user.setId(currentuser.getUid());
        user.setGmail(currentuser.getEmail());


        firebaseDatabase.getReference("Userdetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(UserDetails.class);
                if (user != null) {
                    user.setId(currentuser.getUid());
                    AllMethods.name = user.getGmail();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference = firebaseDatabase.getReference("Digital");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                englishchats.add(message);
                displayMessages(englishchats);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                List<Message> newMessage = new ArrayList<>();
                for (Message m: englishchats){
                    if (m.getKey().equals(message.getKey())){
                        newMessage.add(message);
                    }else {
                        newMessage.add(m);
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                List<Message> newMessage =  new ArrayList<>();

                for (Message m:englishchats){
                    if (!m.getKey().equals(message.getKey())){
                        newMessage.add(m);
                    }
                }
                englishchats = newMessage;
                displayMessages(englishchats);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void displayMessages(List<Message> message) {
        recyclerView.setLayoutManager(new LinearLayoutManager(DigitalchatActivity.this));
        messageAdapter = new MessageAdapter(DigitalchatActivity.this,message,reference);
        recyclerView.setAdapter(messageAdapter);
    }
}
