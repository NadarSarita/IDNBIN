package com.app.idnbin.MainScreen.Chat.Support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

class BotOptAdapter extends RecyclerView.Adapter<BotOptAdapter.BotOptViewHolder> {
    String[] botOptList;
    Context context;
    String uid;
    public BotOptAdapter(Context context, String[] botOptList, String uid) {
    this.botOptList=botOptList;
    this.context=context;
    this.uid=uid;
    }


    @NonNull
    @Override
    public BotOptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflaterView=LayoutInflater.from(context).inflate(R.layout.bot_option_item,parent,false);
        return new BotOptViewHolder(inflaterView);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull BotOptAdapter.BotOptViewHolder holder, int position) {
        holder.txt_bot_option.setText(botOptList[position]);
        holder.txt_bot_option.setOnClickListener(view -> {

            String msg=holder.txt_bot_option.getText().toString();
            ((SupportChatActivity) context).addChatToDb("user",uid,msg);
            ((SupportChatActivity) context).getBotReply(uid,msg);
            ((SupportChatActivity) context).setBotOptionAdapter(position+1);
        });
    }

    @Override
    public int getItemCount() {
        return botOptList.length;
    }

    public class BotOptViewHolder extends RecyclerView.ViewHolder {
        TextView txt_bot_option;
        public BotOptViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_bot_option=itemView.findViewById(R.id.txt_bot_option);
        }
    }

    interface BotOptionInterface {
        void replaceRecyclerViewData(int position);
    }

}
