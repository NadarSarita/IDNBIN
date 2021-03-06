package com.app.idnbin.MainScreen.Chat.Support;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.LoginRegister.UserDetails;
import com.app.idnbin.MainScreen.Chat.Model.Message;
import com.app.idnbin.MainScreen.Chat.Support.Model.BotReply;
import com.app.idnbin.MainScreen.Chat.Support.Model.BotResponse;
import com.app.idnbin.MainScreen.Chat.Support.Model.SupportRequestBody;
import com.app.idnbin.R;
import com.app.idnbin.util.ApiCall;
import com.app.idnbin.util.ApiIterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.idnbin.util.GlobalConstants.ChatsInstance;

public class SupportChatActivity extends AppCompatActivity implements View.OnClickListener {
    SupportChatAdapter supportChatAdapter;
    UserDetails user;
    List<Message> englishchats;
    RecyclerView recyclerView, botOptRecyclerView;
    ImageButton btn_send;
    EditText text_send;
    FirebaseUser currentuser;
    BotOptAdapter botOptAdapter;
    RelativeLayout rl_typing;
    ArrayList<Chat> mchat=new ArrayList<>();
    String[][] botOptArray={ {"HOW TO TRADE?", "HOW TO DEPOSIT?","HOW TO WITHDRAW?"},
            {"TRADE CRYPTO", "TRADE FOREX", "TRADE STOCKS", "TRADE OPTIONS", "TRADE DIGITAL", "TRADE COMMODITIES"},
            {"MY DEPOSIT IS UNSUCCESSFUL", "HOW TO TRADE?"},
            {"GIVE ME AN EXAMPLE", "WITHDRAW TO THE BANK CARD", "HOW TO VERIFY?", "I AM FROM BRAZIL"} };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = findViewById(R.id.message_view);
        botOptRecyclerView=findViewById(R.id.bot_option);
        rl_typing=findViewById(R.id.rl_typing);
        btn_send = findViewById(R.id.btn_send);
        init();

    }
    private void init(){
        currentuser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        user = new UserDetails();

        btn_send.setOnClickListener(this);

        text_send = findViewById(R.id.text_send);
        englishchats = new ArrayList<>();

        supportChatAdapter = new SupportChatAdapter(SupportChatActivity.this, mchat, currentuser.getUid());
        recyclerView.setAdapter(supportChatAdapter);

        setBotOptionAdapter(0);
        displayAllMessages();
    }

    public void setBotOptionAdapter(int i) {
        botOptAdapter= new BotOptAdapter(SupportChatActivity.this, botOptArray[i], currentuser.getUid());
        botOptRecyclerView.setAdapter(botOptAdapter);
    }

    private void setRecyclerEndView(int i) {
        recyclerView.scrollToPosition(i);
    }

    private void displayTypingText(){
        if(rl_typing.getVisibility()==View.GONE)
        rl_typing.setVisibility(View.VISIBLE);
        else
            rl_typing.setVisibility(View.GONE);
    }

    private void showOrHideBotOption(){
        if(botOptRecyclerView.getVisibility()==View.GONE)
            botOptRecyclerView.setVisibility(View.VISIBLE);
    else
            botOptRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
            if (!TextUtils.isEmpty(text_send.getText().toString())){
                String msg=text_send.getText().toString();
                addChatToDb("user",currentuser.getUid(),msg);
                getBotReply(currentuser.getUid(),msg);
            }
    }

    public void addChatToDb(String sentBy, String uid, String msg) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        String str_time = dateFormat.format(new Date());

        DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
        String str_date = dateFormat2.format(new Date());

        if(sentBy.equalsIgnoreCase("Bot"))
        {
            Chat message = new Chat("idnbot",uid,"Support",msg,str_date,str_time);
            ChatsInstance.push().setValue(message);
        }
        else {
            Chat message = new Chat(uid,"idnbot","Support",msg,str_date,str_time);
            if(text_send!=null)
            text_send.setText("");
            ChatsInstance.push().setValue(message);
        }
        showOrHideBotOption();
        displayTypingText();

    }

    public void getBotReply(String uid, String msg) {
        ApiIterface apiIterface = ApiCall.getSupportRetrofit().create(ApiIterface.class);
        Call<BotReply> call = apiIterface.getSupportData(new SupportRequestBody("text",msg));
        call.enqueue(new Callback<BotReply>() {
            @Override
            public void onResponse(@NonNull Call<BotReply> call, @NonNull Response<BotReply> response) {
                if (response.code() == 200) {
                    Log.i("TAG", "if code onResponse: "+response.code());
                    if (response.body() != null) {
                        List<BotResponse> replyList=response.body().getResponses();
                        Log.i("TAG", "inside body onResponse: "+replyList.get(replyList.size()-1).getText());
//                        supportChatAdapter.notifyDataSetChanged();
                        addChatToDb("bot",currentuser.getUid(),replyList.get(replyList.size()-1).getText());
                    }
                }
                else {
                    Log.i("TAG", "else code onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BotReply> call, @NonNull Throwable t) {
                Log.d("TAG", "failure onResponse" + t.getMessage());

            }
        });

    }

    public void displayAllMessages(){
        ChatsInstance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                        mchat.add(chat);
                }
                if(!mchat.isEmpty())
                {
                    supportChatAdapter.notifyDataSetChanged();
                    setRecyclerEndView(mchat.size()-1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
