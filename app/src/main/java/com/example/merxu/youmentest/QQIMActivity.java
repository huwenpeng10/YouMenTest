package com.example.merxu.youmentest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by MerXu on 2017/9/20.
 */

public class QQIMActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ArrayList<bean> bean = new ArrayList<>();
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qqimactivity);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
        initview();
        initdata();
    }

    private void initdata() {
        Message message = mHandler.obtainMessage();
        message.what = 0;
        mHandler.sendMessage(message);
    }

    private void initview() {
        mRecyclerView = (RecyclerView) findViewById(R.id.qq_recyclerview);
    }
}
