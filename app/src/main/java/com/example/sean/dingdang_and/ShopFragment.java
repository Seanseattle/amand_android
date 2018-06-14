package com.example.sean.dingdang_and;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class ShopFragment extends Fragment{

    private List<Message> shopLists = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shop_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.shop_lists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        setShopLists();
        MessageAdapter messageAdapter = new MessageAdapter(shopLists);
        recyclerView.setAdapter(messageAdapter);
    }

    private void setShopLists(){
        shopLists = DataSupport.where("type = ?", "shoplist").find(Message.class);
        if(shopLists == null || shopLists.size() == 0){
            for(int i = 0; i < 20;i++){
                Message message = new Message("一箱苹果", (long)1528968178, "shoplist");
                shopLists.add(message);
            }
        }
    }
}
