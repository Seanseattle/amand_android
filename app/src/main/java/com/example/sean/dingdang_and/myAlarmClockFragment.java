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

public class myAlarmClockFragment extends Fragment {
    private List<Message> myAlarmClockList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.clock_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.clock_lists);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        initmyAlarmClock();
        MessageAdapter adapter=new MessageAdapter(myAlarmClockList);
        recyclerView.setAdapter(adapter);
    }

    private void initmyAlarmClock(){
        myAlarmClockList = DataSupport.where("type = ?", "clocklist").find(Message.class);
        if(myAlarmClockList==null || myAlarmClockList.size()==0){
            for(int i=0;i<10;i++){
                Message message = new Message("12时30分", (long)1528968178, "clocklist");
                myAlarmClockList.add(message);
            }
        }
    }
}
