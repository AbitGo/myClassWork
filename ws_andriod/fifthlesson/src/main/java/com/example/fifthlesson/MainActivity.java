package com.example.fifthlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RatingBar ratingBar;
    private Button submit;
    private CheckBox football, basketball, badminton;

    private Spinner spinner;
    private TextView innerShow;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        //设置radioButton的点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        Toast.makeText(MainActivity.this, "中专", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButton2:
                        Toast.makeText(MainActivity.this, "大专", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButton3:
                        Toast.makeText(MainActivity.this, "本科", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButton4:
                        Toast.makeText(MainActivity.this, "研究生", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, rating + "", Toast.LENGTH_LONG).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = "";
                if (football.isChecked()) {
                    info += "足球 ";
                }
                if (basketball.isChecked()) {
                    info += "篮球 ";
                }
                if (badminton.isChecked()) {
                    info += "羽毛球 ";
                }
                Toast.makeText(MainActivity.this, info, Toast.LENGTH_LONG).show();
            }
        });

        //对于spinner的布局可以os定义的，也可以自定义
//        String[] provices = new String[]{"江苏省","广东省","四川省"};
//        arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_dropdown_item_1line,provices);
//
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.myprovice,android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //innerShow.setText(spinner.getSelectedItem().toString());
                innerShow.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void initView() {
        radioGroup = findViewById(R.id.rd);
        radioButton = findViewById(R.id.radioButton1);
        ratingBar = findViewById(R.id.ratingBar);
        football = findViewById(R.id.football);
        basketball = findViewById(R.id.basketball);
        badminton = findViewById(R.id.badminton);
        submit = findViewById(R.id.submit);
        spinner = findViewById(R.id.spinner);
        innerShow = findViewById(R.id.innerShow);
    }
}
