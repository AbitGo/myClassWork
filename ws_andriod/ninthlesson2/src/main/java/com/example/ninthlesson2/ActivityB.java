package com.example.ninthlesson2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        textView = findViewById(R.id.tvb);
        Intent intent = getIntent();
        String param = intent.getStringExtra("param");
        textView.setText(param);
    }
}
