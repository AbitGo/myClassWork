package com.example.sixthwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //实例化这玩意
    private List<LinearLayout> menuItemList = new ArrayList<>();

    private static final String TAG = "MainActivity-->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = findViewById(R.id.tanHost);
        //初始化
        tabHost.setup();
        //加标签
        tabHost.addTab(tabHost.newTabSpec("111").setIndicator(getMenuItem(R.drawable.zhuye,"主页")).setContent(R.id.textview1));
        tabHost.addTab(tabHost.newTabSpec("222").setIndicator(getMenuItem(R.drawable.fenlei,"分类")).setContent(R.id.textview2));
        tabHost.addTab(tabHost.newTabSpec("222").setIndicator(getMenuItem(R.drawable.gouwuche,"购物车")).setContent(R.id.textview3));
        tabHost.addTab(tabHost.newTabSpec("333").setIndicator(getMenuItem(R.drawable.wode,"我的")).setContent(R.id.textview4));
    }
    //第一个参数图片 第二个名称
    public View getMenuItem(int imgID, String textID){
        LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.test, null);
        ImageView imgView = ll.findViewById(R.id.icon);
        imgView.setBackgroundResource(imgID);
        TextView textView = ll.findViewById(R.id.name);
        textView.setText(textID);
        menuItemList.add(ll);
        return ll;
    }
}
