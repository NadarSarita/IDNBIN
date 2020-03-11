package com.app.idnbin.MainScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.idnbin.MainScreen.Chat.ChatFragment;
import com.app.idnbin.MainScreen.Home.HomeFragment;
import com.app.idnbin.MainScreen.MarketAnalysis.MarketAnalysisFragment;
import com.app.idnbin.MainScreen.Portfolio.PortfolioFragment;
import com.app.idnbin.MainScreen.Profile.ProfileFragment;
import com.app.idnbin.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView IvPortfolio,IvMarketAnalysis,IvHome,IvChat,IvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        IvPortfolio = findViewById(R.id.IvPortfolio);
        IvMarketAnalysis = findViewById(R.id.IvMarketAnalysis);
        IvHome = findViewById(R.id.IvHome);
        IvChat = findViewById(R.id.IvChat);
        IvProfile = findViewById(R.id.IvProfile);

        IvPortfolio.setOnClickListener(this);
        IvMarketAnalysis.setOnClickListener(this);
        IvHome.setOnClickListener(this);
        IvChat.setOnClickListener(this);
        IvProfile.setOnClickListener(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FragmentHolder, new HomeFragment());
        fragmentTransaction.commit();
        IvPortfolio.setImageResource(R.drawable.ic_portfolio);
        IvMarketAnalysis.setImageResource(R.drawable.ic_market_analysis);
        IvHome.setImageResource(R.drawable.ic_home_selected);
        IvChat.setImageResource(R.drawable.ic_chat);
        IvProfile.setImageResource(R.drawable.ic_profile);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.IvPortfolio:
                fragmentTransaction.replace(R.id.FragmentHolder, new PortfolioFragment());
                IvPortfolio.setImageResource(R.drawable.ic_portfolio_selected);
                IvMarketAnalysis.setImageResource(R.drawable.ic_market_analysis);
                IvHome.setImageResource(R.drawable.ic_home);
                IvChat.setImageResource(R.drawable.ic_chat);
                IvProfile.setImageResource(R.drawable.ic_profile);
                break;
            case R.id.IvMarketAnalysis:
                fragmentTransaction.replace(R.id.FragmentHolder, new MarketAnalysisFragment());
                IvPortfolio.setImageResource(R.drawable.ic_portfolio);
                IvMarketAnalysis.setImageResource(R.drawable.ic_market_analysis_selected);
                IvHome.setImageResource(R.drawable.ic_home);
                IvChat.setImageResource(R.drawable.ic_chat);
                IvProfile.setImageResource(R.drawable.ic_profile);
                break;
            case R.id.IvHome:
                fragmentTransaction.replace(R.id.FragmentHolder, new HomeFragment());
                IvPortfolio.setImageResource(R.drawable.ic_portfolio);
                IvMarketAnalysis.setImageResource(R.drawable.ic_market_analysis);
                IvHome.setImageResource(R.drawable.ic_home_selected);
                IvChat.setImageResource(R.drawable.ic_chat);
                IvProfile.setImageResource(R.drawable.ic_profile);
                break;
            case R.id.IvChat:
                fragmentTransaction.replace(R.id.FragmentHolder, new ChatFragment());
                IvPortfolio.setImageResource(R.drawable.ic_portfolio);
                IvMarketAnalysis.setImageResource(R.drawable.ic_market_analysis);
                IvHome.setImageResource(R.drawable.ic_home);
                IvChat.setImageResource(R.drawable.ic_chat_selected);
                IvProfile.setImageResource(R.drawable.ic_profile);
                break;
            case R.id.IvProfile:
                fragmentTransaction.replace(R.id.FragmentHolder, new ProfileFragment());
                IvPortfolio.setImageResource(R.drawable.ic_portfolio);
                IvMarketAnalysis.setImageResource(R.drawable.ic_market_analysis);
                IvHome.setImageResource(R.drawable.ic_home);
                IvChat.setImageResource(R.drawable.ic_chat);
                IvProfile.setImageResource(R.drawable.ic_profile_selected);
                break;
        }
        fragmentTransaction.commit();
    }
}
