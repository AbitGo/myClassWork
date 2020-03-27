package com.example.eighthgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ImageView[] imgs = new ImageView[3];
    private int[] imgId = {R.id.img1, R.id.img2, R.id.img3};
    private RadioButton rb0, rb1;
    private ImageButton imageButton;
    private ProgressBar progressBar;
    private ToggleButton toggleButton;
    private int level = 10;
    private int cardId[] = {R.mipmap.card1, R.mipmap.card2, R.mipmap.card3};

    private int index;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            progressBar.setProgress(level);
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (level >= 0) {
                Message message = Message.obtain();
                handler.sendMessage(message);
                try {
                    Thread.sleep(1000);
                    level--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();

        rb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    level = 10;
            }
        });

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    level = 5;
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置倒计时
                progressBar.setMax(level);
                new Thread(runnable).start();
                 index = (int) (Math.random() * 10 % 3);
                //交换
                int temp= cardId[index];
                cardId[index] = cardId[0];
                cardId[0] = temp;
                //恢复原状
                for (int i = 0; i < cardId.length; i++) {
                    imgs[i].setImageResource(R.mipmap.cards);
                    imgs[i].setOnClickListener(new myLisntener());
                }
            }
        });

    }

    void initView() {
        imageButton = findViewById(R.id.btn);
        rb0 = findViewById(R.id.rb0);
        rb1 = findViewById(R.id.rb1);
        for (int i = 0; i < imgId.length; i++) {
            imgs[i] = findViewById(imgId[i]);
        }
        progressBar = findViewById(R.id.pd);
        toggleButton = findViewById(R.id.tb);
    }

    class myLisntener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img1:
                    imgs[0].setImageResource(cardId[0]);
                    if(index==0){
                        Toast.makeText(MainActivity.this,"猜对了",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.img2:
                    imgs[1].setImageResource(cardId[1]);
                    if(index==1){
                        Toast.makeText(MainActivity.this,"猜对了",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.img3:
                    imgs[2].setImageResource(cardId[2]);
                    if(index==2){
                        Toast.makeText(MainActivity.this,"猜对了",Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    }
}
