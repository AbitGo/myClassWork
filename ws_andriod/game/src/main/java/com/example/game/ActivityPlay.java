package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPlay extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playinterface);
        initView();
    }

    void initView(){
        textView = findViewById(R.id.msg);
        Intent intent = getIntent();
        String roomId = intent.getStringExtra("roomId");
        textView.setText(roomId);
    }
}
