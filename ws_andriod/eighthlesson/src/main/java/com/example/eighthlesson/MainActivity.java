package com.example.eighthlesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.sql.BatchUpdateException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    ToggleButton toggleButton;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myswitch);
        initView();
    }

    void initView(){
        aSwitch = findViewById(R.id.sw);
        toggleButton = findViewById(R.id.tb);
        imageView = findViewById(R.id.ledswitch);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imageView.setImageResource(R.mipmap.ledopen);
                }else {
                    imageView.setImageResource(R.mipmap.ledclose);
                }
            }
        });
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imageView.setImageResource(R.mipmap.ledopen);
                }else {
                    imageView.setImageResource(R.mipmap.ledclose);
                }
            }
        });
    }
}



//public class MainActivity extends AppCompatActivity {
//    Button button;
//    TextView textView;
//    ProgressBar progressBar;
//    int process = 0;
//
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            textView.setText(msg.arg1+"%");
//            progressBar.setProgress(msg.arg1);
//        }
//    };
//
//    Runnable runable = new Runnable() {
//        @Override
//        public void run() {
//            while (process<100){
//                Message msg = Message.obtain();
//                msg.arg1 = process;
//                handler.sendMessage(msg);
//                try {
//                    Thread.sleep(1000);
//                    process++;
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dp);
//        initView();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Thread thread = new Thread(runable);
//                thread.start();
//            }
//        });
//    }
//
//    void initView(){
//        progressBar = findViewById(R.id.progressbar);
//        button = findViewById(R.id.button);
//        textView = findViewById(R.id.tvinfo);
//    }
//}
