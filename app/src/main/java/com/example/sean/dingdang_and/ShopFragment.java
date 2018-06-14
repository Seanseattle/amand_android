package com.example.sean.dingdang_and;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import org.litepal.crud.DataSupport;

import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private List<Message> shopLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setShopLists();
        RecyclerView recyclerView = findViewById(R.id.slid_content);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter adapter = new MessageAdapter(shopLists);
        recyclerView.setAdapter(adapter);
    }


    private void setShopLists(){
        shopLists = DataSupport.where("type = ?", "shoplist").find(Message.class);
    }
}
