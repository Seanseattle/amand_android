package com.example.sean.dingdang_and;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment{

    private List<Message> noteLists = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_frament, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.note_lists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        setShopLists();
        MessageAdapter messageAdapter = new MessageAdapter(noteLists);
        recyclerView.setAdapter(messageAdapter);
    }

    private void setShopLists(){
        noteLists = DataSupport.where("type = ?", "notelist").find(Message.class);
        if(noteLists == null || noteLists.size() == 0){
            for(int i = 0; i < 20;i++){
                Message message = new Message("这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备忘录这是一个备", (long)1528968178, "notelist");
                noteLists.add(message);
            }
        }
    }
}
