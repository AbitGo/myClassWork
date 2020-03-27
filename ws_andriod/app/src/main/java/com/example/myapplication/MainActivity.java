package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取名字
        this.findViewById(R.id.textview);
        setContentView(R.layout.activity_main);

        //显示bar中的logo
        ActionBar actionBar = getSupportActionBar();
        //获取路径
        actionBar.setIcon(R.drawable.test_icon);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }
}
