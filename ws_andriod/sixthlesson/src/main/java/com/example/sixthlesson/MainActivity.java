package com.example.sixthlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        initView();
        tabHost.setup();
        TabHost.TabSpec tcRed = tabHost.newTabSpec("layoutred").setIndicator("红色").setContent(R.id.layoutred);
        tabHost.addTab(tcRed);
        TabHost.TabSpec tcyel = tabHost.newTabSpec("layoutyellow").setIndicator("黄色").setContent(R.id.layoutyellow);
        tabHost.addTab(tcyel);
    }
    void initView(){
        tabHost = this.findViewById(R.id.mytabhost);
    }
}
