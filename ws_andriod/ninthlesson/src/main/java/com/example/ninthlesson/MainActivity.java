package com.example.ninthlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private ArrayList arrayList;
    private int[] imageId = {R.mipmap.tx1,R.mipmap.tx2,R.mipmap.tx3,R.mipmap.tx4};
    private List<String> name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }
    void initView(){
        arrayList = new ArrayList();
        listView = findViewById(R.id.lv);
//        for(int i = 0;i<imageId.length;i++){
//            HashMap<String,Object> hashMap = new HashMap<>();
////            hashMap.put("itemImg",imageId[i]);
//            hashMap.put("userdefineitemName",name[i]);
//            hashMap.put("userdefineitemTop","置顶");
//            hashMap.put("userdefineitemdelete","删除");
////            hashMap.put("itemlaji",R.mipmap.laji);
//            arrayList.add(hashMap);
//        }
        //arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,week);

//        String form[] = {"itemImg","itemName","itemlaji"};
////        int to[] = {R.id.udviv1,R.id.udvtv,R.id.udviv2};
//        String form[] = {"userdefineitemName","userdefineitemTop","userdefineitemdelete"};
//        int to[] = {R.id.udvtv111,R.id.tv_top111,R.id.tv_delete111};
//        simpleAdapter = new SimpleAdapter(MainActivity.this,arrayList,R.layout.innerview,form,to);
//        listView.setAdapter(simpleAdapter);
        name = new ArrayList<>();
        name.add("一");
        name.add("二");
        name.add("三");
        name.add("四");
        MyAdapter myAdapter =new MyAdapter(this,name);
        listView.setAdapter(myAdapter);
    }
}

//public class MainActivity extends AppCompatActivity {
//    private ListView listView;
//    private ArrayAdapter arrayAdapter;
//    private String[] week = {"周一","周二","周三","周四","周五","周六","周日"};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.item);
//        initView();
//    }
//    void initView(){
//        listView = findViewById(R.id.lv);
//        //arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,week);
//        arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,week);
//        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //Toast.makeText(MainActivity.this,week[position],Toast.LENGTH_SHORT).show();
//                SparseBooleanArray checked = listView.getCheckedItemPositions();
//                String temp = "";
//                for(int i = 0;i<week.length;i++){
//                    if(checked.get(i)){
//                        temp+=week[i]+" ";
//                    }
//                }
//                Toast.makeText(MainActivity.this,temp,Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
