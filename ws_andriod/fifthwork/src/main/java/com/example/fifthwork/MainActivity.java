package com.example.fifthwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Spinner spinner1, spinner2, spinner3;
    private OkHttpUtils okHttpUtils;

    String BaseUrl = "http://guolin.tech/api/china";
    String[] noAnyElem = new String[]{""};

    //省
    int provNum = 1;
    String[] provArray;
    //市
    int cityNum = 1;
    String[] cityArray;
    Map<String,Integer> cityMap;
    //区
    String[] districtArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("workfive");
        setSupportActionBar(toolbar);
        initView();
        getProvinces();

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provNum = position+1;
                getCities();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cityName = parent.getItemAtPosition(position).toString();
                cityNum = cityMap.get(cityName);
                getDistricts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void initView() {
        textView = findViewById(R.id.show);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
    }

    void getProvinces() {
        okHttpUtils = OkHttpUtils.getInstance();
        okHttpUtils.getRequest(BaseUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                showMessageAllProv(json);
            }

            public void showMessageAllProv(String str) {
                runOnUiThread(() -> {
                    try {
                        JSONArray jsonArray = new JSONArray(str);
                        provArray = new String[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject param = JSONObject.parseObject(jsonArray.get(i).toString());
                            provArray[i] = param.getString("name");
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, provArray);
                            spinner1.setAdapter(arrayAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
    void getCities() {
        okHttpUtils = OkHttpUtils.getInstance();
        okHttpUtils.getRequest(BaseUrl+"/"+provNum, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                showMessageAllCities(json);
            }

            public void showMessageAllCities(String str) {
                runOnUiThread(() -> {
                    try {
                        JSONArray jsonArray = new JSONArray(str);
                        cityArray = new String[jsonArray.length()];
                        cityMap = new HashMap<String,Integer>();
                        if(cityMap!=null){
                            cityMap.clear();
                        }
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject param = JSONObject.parseObject(jsonArray.get(i).toString());
                            cityArray[i] = param.getString("name");
                            cityMap.put(param.getString("name"),param.getInteger("id"));
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, cityArray);
                            spinner2.setAdapter(arrayAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
    void getDistricts() {
        okHttpUtils = OkHttpUtils.getInstance();
        okHttpUtils.getRequest(BaseUrl+"/"+provNum+"/"+cityNum, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                showMessageAllDis(json);
            }

            public void showMessageAllDis(String str) {
                runOnUiThread(() -> {
                    try {
                        JSONArray jsonArray = new JSONArray(str);
                        districtArray = new String[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject param = JSONObject.parseObject(jsonArray.get(i).toString());
                            districtArray[i] = param.getString("name");
                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line, districtArray);
                            spinner3.setAdapter(arrayAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}
