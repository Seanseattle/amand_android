package com.example.sean.dingdang_and;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.litepal.LitePalApplication;

public class ContextSave extends Activity{
    public static Context CurrentContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentContext = this;
    }


}
