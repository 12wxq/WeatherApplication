package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.weatherapplication.adapter.TipsAdapter;
import com.example.weatherapplication.bean.DayWeatherBean;

public class TipsActivity extends AppCompatActivity {
    private RecyclerView rlvTips;
    private TipsAdapter mTipsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        rlvTips = findViewById(R.id.rlv_tips);
        Intent intent=getIntent();
       DayWeatherBean weatherBean=(DayWeatherBean) intent.getSerializableExtra("tips");
       if (weatherBean==null){
           return;
       }
        mTipsAdapter=new TipsAdapter(this,weatherBean.getmTipsBeans());
       rlvTips.setAdapter(mTipsAdapter);
       rlvTips.setLayoutManager(new LinearLayoutManager(this));
    }
}