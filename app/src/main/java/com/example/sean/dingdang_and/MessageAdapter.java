package com.example.sean.dingdang_and;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private List<Message> messageList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView message ;
        long id;
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
        final ViewHolder holder = new ViewHolder(view);
        holder.cancalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int message_postion = holder.getAdapterPosition();
                final Message message = messageList.get(message_postion);
                DataSupport.delete(Message.class, message.getId());
                messageList.remove(message_postion);
                notifyItemRemoved(message_postion);
                notifyItemRangeChanged(0, messageList.size());
//                AlertDialog.Builder dialog = new AlertDialog.Builder(ContextSave.CurrentContext);
//                dialog.setCancelable(true);
//                dialog.setTitle("删除商品");
//                dialog.setMessage("您要删除该商品吗");
//                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DataSupport.delete(Message.class, message.getId());
//                        messageList.remove(message_postion);
//                        notifyItemRemoved(message_postion);
//                        notifyItemRangeChanged(0, messageList.size());
//
//                    }
//                });
//                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.create();
//                dialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Message message = messageList.get(position);
        holder.message.setText(message.getMessage());
        holder.cancalButton.setText("取消");
        holder.id = message.getId();
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
