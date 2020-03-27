package com.example.ninthlesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends android.widget.BaseAdapter {

    private List<String> data;

    private Context context;

    public MyAdapter(Context context, List<String> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载视图
        convertView = LayoutInflater.from(context).inflate(R.layout.userdefineitem,null);
        //初始化控件
        TextView tv_top = convertView.findViewById(R.id.tv_top);
        TextView tv_delete = convertView.findViewById(R.id.tv_delete);
        TextView tv = convertView.findViewById(R.id.udvtv);
        tv.setText(data.get(position));
        tv_delete.setOnClickListener(v -> {
            //移除
            data.remove(position);
            //通知ListView更新
            notifyDataSetChanged();
            Toast.makeText(context,"click " + position,Toast.LENGTH_SHORT).show();
        });
        return convertView;
    }

}
