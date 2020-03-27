package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class thirdlesson_hw extends AppCompatActivity {
    //清零，删除上一字符，等于
    Button btnC, btnDel, btnIs;
    StringBuffer digtalA = new StringBuffer();
    StringBuffer digtalB = new StringBuffer();
    StringBuffer param = new StringBuffer();
    //好比 1+1 3*4 这种
    StringBuffer paramAll = new StringBuffer();
    private boolean isChar = false;
    int operateChar = 0;

    Button[] buttons_num = new Button[11];
    int[] btnId_num = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonDOT};

    //放 + - * /的按钮
    Button[] buttons_operate = new Button[4];
    //+ - * /
    int[] btnId_operate = {R.id.buttonPLUS, R.id.buttonSUB, R.id.buttonMUL, R.id.buttonDEV};
    //用来显示结果
    TextView resultText;
    //用来显示当前得运算，例如 3*4 2+4
    TextView paramText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置内容视图
        setContentView(R.layout.activity_thirdlesson_hw);

        //实例化对象
        initView();
        //绑定监听事件
        //也可以在xml中这样写
        //android:onClick="myClick"
        myClick myClickClass = new myClick();
        for (int i = 0; i < buttons_num.length; i++) {
            buttons_num[i].setOnClickListener(myClickClass);
        }
        for (int i = 0; i < buttons_operate.length; i++) {
            buttons_operate[i].setOnClickListener(myClickClass);
        }

        btnIs = this.findViewById(R.id.buttonIS);
        btnIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digtalB = param;
                //运算
                if (isChar == true) {
                    float a = Float.valueOf(digtalA.toString());
                    float b = Float.valueOf(digtalB.toString());
                    isChar = false;
                    digtalA = new StringBuffer();
                    digtalB = new StringBuffer();
                    param = new StringBuffer();
                    float result = 0.0f;
                    switch (operateChar) {
                        case 0:
                            result = a + b;
                            break;
                        case 1:
                            result = a - b;
                            break;
                        case 2:
                            result = a * b;
                            break;
                        case 3:
                            result = a / b;
                            break;
                    }
                    paramAll = new StringBuffer(result+"");
                    param = new StringBuffer(result + "");
                    resultText.setText(result + "");
                }
            }
        });

        btnDel = this.findViewById(R.id.buttonDEL);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除最后一个
                if (!param.toString().equals("")) {
                    param = param.deleteCharAt(param.length() - 1);
                    paramAll = paramAll.deleteCharAt(paramAll.length()-1);
                    paramText.setText(paramAll.toString());
                    resultText.setText(param.toString());
                }
            }
        });

        btnC = this.findViewById(R.id.buttonC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清零
                isChar = false;
                digtalA = new StringBuffer();
                digtalB = new StringBuffer();
                param = new StringBuffer();
                paramAll = new StringBuffer();
                resultText.setText("");
                paramText.setText("");
            }
        });
    }

    void initView() {
        for (int i = 0; i < btnId_num.length; i++) {
            buttons_num[i] = this.findViewById(btnId_num[i]);
        }
        for (int i = 0; i < btnId_operate.length; i++) {
            buttons_operate[i] = this.findViewById(btnId_operate[i]);
        }
        resultText = this.findViewById(R.id.resultString);
        paramText = this.findViewById(R.id.paramString);
    }

    class myClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button0:
                    param.append("0");
                    paramAll.append("0");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button1:
                    param.append("1");
                    paramAll.append("1");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button2:
                    param.append("2");
                    paramAll.append("2");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button3:
                    param.append("3");
                    paramAll.append("3");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button4:
                    param.append("4");
                    paramAll.append("4");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button5:
                    param.append("5");
                    paramAll.append("5");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button6:
                    param.append("6");
                    paramAll.append("6");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button7:
                    param.append("7");
                    paramAll.append("7");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button8:
                    param.append("8");
                    paramAll.append("8");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.button9:
                    param.append("9");
                    paramAll.append("9");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.buttonDOT:
                    param.append(".");
                    paramAll.append(".");
                    paramText.setText(paramAll.toString());
                    break;
                case R.id.buttonPLUS:
                    //进行加运算
                    digtalA = param;
                    isChar = true;
                    param = new StringBuffer();
                    paramAll.append("+");
                    paramText.setText(paramAll.toString());
                    //进行清空
                    resultText.setText("");
                    operateChar = 0;
                    break;
                case R.id.buttonSUB:
                    //进行-运算
                    digtalA = param;
                    isChar = true;
                    param = new StringBuffer();
                    paramAll.append("-");
                    paramText.setText(paramAll.toString());
                    //进行清空
                    resultText.setText("");
                    operateChar = 1;
                    break;
                case R.id.buttonMUL:
                    //进行*运算
                    digtalA = param;
                    isChar = true;
                    param = new StringBuffer();
                    paramAll.append("×");
                    paramText.setText(paramAll.toString());
                    //进行清空
                    resultText.setText("");
                    operateChar = 2;
                    break;
                case R.id.buttonDEV:
                    //进行/运算
                    digtalA = param;
                    isChar = true;
                    param = new StringBuffer();
                    paramAll.append("÷");
                    paramText.setText(paramAll.toString());
                    //进行清空
                    resultText.setText("");
                    operateChar = 3;
                    break;
            }
        }
    }

}

