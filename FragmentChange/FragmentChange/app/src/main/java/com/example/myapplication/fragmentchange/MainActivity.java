package com.example.myapplication.fragmentchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.fragmentchange.fragment.HomeFragment;
import com.example.myapplication.fragmentchange.fragment.MsgFragment;
import com.example.myapplication.fragmentchange.fragment.TopFragment;
import com.example.myapplication.fragmentchange.fragment.ZoneFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Fragment> fragmentList;

    private static int lastIndex = 0;

    private TextView tv_home;

    private TextView tv_msg;

    private TextView tv_zone;

    private TextView tv_top;

    public static final int HOME_FRAGMENT = 0;
    public static final int ZONE_FRAGMENT = 1;
    public static final int MSG_FRAGMENT = 2;
    public static final int TOP_FRAGMENT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_home = findViewById(R.id.tv_home);
        tv_zone = findViewById(R.id.tv_zone);
        tv_msg = findViewById(R.id.tv_msg);
        tv_top = findViewById(R.id.tv_top);
        tv_home.setOnClickListener(this);
        tv_zone.setOnClickListener(this);
        tv_msg.setOnClickListener(this);
        tv_top.setOnClickListener(this);
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ZoneFragment());
        fragmentList.add(new MsgFragment());
        fragmentList.add(new TopFragment());
        //default fragment
        switchFragment(HOME_FRAGMENT);
    }

    //不用加入回退栈中直接hide
    public void switchFragment(int showFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //要显示的Fragment
        Fragment currentFragment = fragmentList.get(showFragment);
        //要隐藏的Fragment
        Fragment hideFragment = fragmentList.get(lastIndex);
        //将当前的展示Fragment赋值给隐藏的索引下次执行此方法就可隐藏
        lastIndex = showFragment;
        transaction.hide(hideFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            transaction.add(R.id.fragmentLayout, currentFragment);
        }
        transaction.show(currentFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home:
                switchFragment(HOME_FRAGMENT);
                break;
            case R.id.tv_zone:
                switchFragment(ZONE_FRAGMENT);
                break;
            case R.id.tv_msg:
                switchFragment(MSG_FRAGMENT);
                break;
            case R.id.tv_top:
                switchFragment(TOP_FRAGMENT);
                break;
        }
    }
}