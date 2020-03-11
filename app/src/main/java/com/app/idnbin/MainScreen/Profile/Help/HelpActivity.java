package com.app.idnbin.MainScreen.Profile.Help;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.app.idnbin.MainScreen.Profile.Help.Educational.EducationFragment;
import com.app.idnbin.MainScreen.Profile.Help.FAQ.FAQFragment;
import com.app.idnbin.R;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView IV_help,IV_education,IV_faq,IV_livechat;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        IV_help = findViewById(R.id.IV_help);
        IV_education = findViewById(R.id.IV_education);
        IV_faq = findViewById(R.id.IV_faq);
        IV_livechat = findViewById(R.id.IV_livechat);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Help");


        IV_help.setOnClickListener(this);
        IV_education.setOnClickListener(this);
        IV_faq.setOnClickListener(this);
        IV_livechat.setOnClickListener(this);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framLayout, new HelpFragment());
        fragmentTransaction.commit();
        IV_help.setImageResource(R.drawable.ic_help_selected);

        IV_education.setImageResource(R.drawable.ic_education);
        IV_faq.setImageResource(R.drawable.ic_faq);
        IV_livechat.setImageResource(R.drawable.ic_chat);


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.IV_help :
                fragmentTransaction.replace(R.id.framLayout, new HelpFragment());
                IV_help.setImageResource(R.drawable.ic_help_selected);
                IV_education.setImageResource(R.drawable.ic_education);
                IV_faq.setImageResource(R.drawable.ic_faq);
                IV_livechat.setImageResource(R.drawable.ic_chat);
                break;

            case R.id.IV_education :
                fragmentTransaction.replace(R.id.framLayout, new EducationFragment());
                IV_help.setImageResource(R.drawable.ic_help);
                IV_education.setImageResource(R.drawable.ic_education_selected);
                IV_faq.setImageResource(R.drawable.ic_faq);
                IV_livechat.setImageResource(R.drawable.ic_chat);
                break;

            case R.id.IV_faq :
                fragmentTransaction.replace(R.id.framLayout, new FAQFragment());
                IV_help.setImageResource(R.drawable.ic_help);
                IV_education.setImageResource(R.drawable.ic_education);
                IV_faq.setImageResource(R.drawable.ic_faq_selected);
                IV_livechat.setImageResource(R.drawable.ic_chat);
                break;

            case R.id.IV_livechat :
                fragmentTransaction.replace(R.id.framLayout, new LiveChatFragment());
                IV_help.setImageResource(R.drawable.ic_help);
                IV_education.setImageResource(R.drawable.ic_education);
                IV_faq.setImageResource(R.drawable.ic_faq);
                IV_livechat.setImageResource(R.drawable.ic_chat_selected);
                break;
        }
        fragmentTransaction.commit();
    }
}
