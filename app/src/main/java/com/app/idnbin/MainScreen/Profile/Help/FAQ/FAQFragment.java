package com.app.idnbin.MainScreen.Profile.Help.FAQ;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FAQFragment extends Fragment implements View.OnClickListener  {

    TextView Tv_Que1,Tv_Que2,Tv_Que3,Tv_Que4,Tv_Que5,Tv_Que6,Tv_Que7,Tv_Que8, Tv_Ans1,Tv_Ans2,Tv_Ans3,Tv_Ans4,Tv_Ans5,Tv_Ans6,Tv_Ans7,Tv_Ans8;
    ImageView Iv_img1,Iv_img2,Iv_img3,Iv_img4,Iv_img5,Iv_img6,Iv_img7,Iv_img8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        Tv_Que1 = view.findViewById(R.id.Tv_Que1);
        Tv_Que2 = view.findViewById(R.id.Tv_Que2);
        Tv_Que3 = view.findViewById(R.id.Tv_Que3);
        Tv_Que4 = view.findViewById(R.id.Tv_Que4);
        Tv_Que5 = view.findViewById(R.id.Tv_Que5);
        Tv_Que6 = view.findViewById(R.id.Tv_Que6);
        Tv_Que7 = view.findViewById(R.id.Tv_Que7);
        Tv_Que8 = view.findViewById(R.id.Tv_Que8);
        Tv_Ans1 = view.findViewById(R.id.Tv_Ans1);
        Tv_Ans2 = view.findViewById(R.id.Tv_Ans2);
        Tv_Ans3 = view.findViewById(R.id.Tv_Ans3);
        Tv_Ans4 = view.findViewById(R.id.Tv_Ans4);
        Tv_Ans5 = view.findViewById(R.id.Tv_Ans5);
        Tv_Ans6 = view.findViewById(R.id.Tv_Ans6);
        Tv_Ans7 = view.findViewById(R.id.Tv_Ans7);
        Tv_Ans8 = view.findViewById(R.id.Tv_Ans8);
        Iv_img1 = view.findViewById(R.id.Iv_img1);
        Iv_img2 = view.findViewById(R.id.Iv_img2);
        Iv_img3 = view.findViewById(R.id.Iv_img3);
        Iv_img4 = view.findViewById(R.id.Iv_img4);
        Iv_img5 = view.findViewById(R.id.Iv_img5);
        Iv_img6 = view.findViewById(R.id.Iv_img6);
        Iv_img7 = view.findViewById(R.id.Iv_img7);
        Iv_img8 = view.findViewById(R.id.Iv_img8);

        Tv_Que1.setOnClickListener(this);
        Tv_Que2.setOnClickListener(this);
        Tv_Que3.setOnClickListener(this);
        Tv_Que4.setOnClickListener(this);
        Tv_Que5.setOnClickListener(this);
        Tv_Que6.setOnClickListener(this);
        Tv_Que7.setOnClickListener(this);
        Tv_Que8.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Tv_Que1:
                if (Tv_Ans1.getVisibility() == View.VISIBLE){
                    Tv_Ans1.setVisibility(View.GONE);
                    Iv_img1.setImageResource(R.drawable.ic_downarrow);
                } else {
                    Tv_Ans1.setVisibility(View.VISIBLE);
                    Iv_img1.setImageResource(R.drawable.ic_uparrow);
                }
                break;
            case R.id.Tv_Que2:
                if (Tv_Ans2.getVisibility() == View.VISIBLE){
                    Tv_Ans2.setVisibility(View.GONE);
                    Iv_img2.setImageResource(R.drawable.ic_downarrow);
                } else {
                    Tv_Ans2.setVisibility(View.VISIBLE);
                    Iv_img2.setImageResource(R.drawable.ic_uparrow);
                }
                break;
            case R.id.Tv_Que3:
                if (Tv_Ans3.getVisibility() == View.VISIBLE){
                    Tv_Ans3.setVisibility(View.GONE);
                    Iv_img3.setImageResource(R.drawable.ic_downarrow);
                } else {
                    Tv_Ans3.setVisibility(View.VISIBLE);
                    Iv_img3.setImageResource(R.drawable.ic_uparrow);
                }
                break;
            case R.id.Tv_Que4:
                if (Tv_Ans4.getVisibility() == View.VISIBLE){
                    Tv_Ans4.setVisibility(View.GONE);
                    Iv_img4.setImageResource(R.drawable.ic_downarrow);
                } else {
                    Tv_Ans4.setVisibility(View.VISIBLE);
                    Iv_img4.setImageResource(R.drawable.ic_uparrow);
                }
                break;
            case R.id.Tv_Que5:
                if (Tv_Ans5.getVisibility() == View.VISIBLE){
                    Tv_Ans5.setVisibility(View.GONE);
                    Iv_img5.setImageResource(R.drawable.ic_downarrow);
                } else {
                    Tv_Ans5.setVisibility(View.VISIBLE);
                    Iv_img5.setImageResource(R.drawable.ic_uparrow);
                }
                break;
            case R.id.Tv_Que6:
                if (Tv_Ans6.getVisibility() == View.VISIBLE){
                    Tv_Ans6.setVisibility(View.GONE);
                    Iv_img6.setImageResource(R.drawable.ic_downarrow);
                } else {
                    Tv_Ans6.setVisibility(View.VISIBLE);
                    Iv_img6.setImageResource(R.drawable.ic_uparrow);
                }
                break;
            case R.id.Tv_Que7:
                if (Tv_Ans7.getVisibility() == View.VISIBLE){
                    Tv_Ans7.setVisibility(View.GONE);
                    Iv_img7.setImageResource(R.drawable.ic_downarrow);
                } else {
                    Tv_Ans7.setVisibility(View.VISIBLE);
                    Iv_img7.setImageResource(R.drawable.ic_uparrow);
                }
                break;
            case R.id.Tv_Que8:
                if (Tv_Ans8.getVisibility() == View.VISIBLE){
                    Tv_Ans8.setVisibility(View.GONE);
                    Iv_img8.setImageResource(R.drawable.ic_downarrow);
                } else {
                    Tv_Ans8.setVisibility(View.VISIBLE);
                    Iv_img8.setImageResource(R.drawable.ic_uparrow);
                }
                break;
        }
    }

}
