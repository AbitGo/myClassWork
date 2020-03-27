package com.example.eighthwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView[] season;
    private TextView textView;
    private int[] seasonid= {R.id.img1,R.id.img2,R.id.img3,R.id.img4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initview();
    }
    void initview(){
        textView = findViewById(R.id.tvinfo);
        season = new ImageView[4];
        for(int i = 0;i<seasonid.length;i++){
            season[i] = findViewById(seasonid[i]);
            //season[i].setOnClickListener(new myLisntener());
            season[i].setOnTouchListener(new touchListener());
        }
    }

    class touchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.img1:
                    textView.setText("春");
                    break;
                case R.id.img2:
                    textView.setText("夏");
                    break;
                case R.id.img3:
                    textView.setText("秋");
                    break;
                case R.id.img4:
                    textView.setText("冬");
                    break;
            }
            return false;
        }
    }

//    class myLisntener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.img1:
//                    textView.setText("春");
//                    break;
//                case R.id.img2:
//                    textView.setText("夏");
//                    break;
//                case R.id.img3:
//                    textView.setText("秋");
//                    break;
//                case R.id.img4:
//                    textView.setText("冬");
//                    break;
//            }
//        }
//    }

}
