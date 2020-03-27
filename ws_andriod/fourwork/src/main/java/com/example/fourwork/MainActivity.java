package com.example.fourwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView[] imageView = new ImageView[4];
    int[] Rid = {R.id.shouye,R.id.mulu,R.id.xiaoxi,R.id.ziyuan};
    Toast toast;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("workfour");
        setSupportActionBar(toolbar);
        initView();
    }

    void initView(){
        for(int i = 0;i<imageView.length;i++){
            imageView[i] = findViewById(Rid[i]);
            imageView[i].setOnClickListener(new myClick());
        }
    }

    class myClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.shouye:
                    Toast.makeText(getApplicationContext(),"首页",Toast.LENGTH_LONG).show();
                    break;
                case R.id.mulu:
                    Toast.makeText(getApplicationContext(),"目录",Toast.LENGTH_LONG).show();
                    break;
                case R.id.xiaoxi:
                    Toast.makeText(getApplicationContext(),"消息",Toast.LENGTH_LONG).show();
                    break;
                case R.id.ziyuan:
                    Toast.makeText(getApplicationContext(),"设置",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
