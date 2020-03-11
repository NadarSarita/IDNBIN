package com.app.idnbin.MainScreen.Chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.idnbin.R;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<String> {
    String[] names;
    int[] countryFlags;
    Context mContext;

    public ChatAdapter(Context context, String[] countryNames, int[] countryFlags) {
        super(context, R.layout.layout_groupchat);
        this.names = countryNames;
        this.countryFlags = countryFlags;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.layout_groupchat, parent, false);
            mViewHolder.IMG_ChatLogo =  convertView.findViewById(R.id.IMG_ChatLogo);
            mViewHolder.TV_chat =  convertView.findViewById(R.id.TV_chat);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.IMG_ChatLogo.setImageResource(countryFlags[position]);
        mViewHolder.TV_chat.setText(names[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView IMG_ChatLogo;
        TextView TV_chat;
    }
}
