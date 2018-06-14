package com.example.sean.dingdang_and;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private List<Message> messageList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView message ;
        Button cancalButton;

        public ViewHolder(View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.message_text);
            this.cancalButton = itemView.findViewById(R.id.message_button);
        }
    }

    public MessageAdapter(List<Message> messageList){
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.message.setText(message.getMessage());
        holder.cancalButton.setText("点击取消");
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
