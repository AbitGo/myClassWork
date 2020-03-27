package com.example.thirdlesson;

public class uselessfile {
//public class MainActivity_2 extends AppCompatActivity {
//    //清零，删除上一字符，等于
//    Button btnC, btnDel, btnIs;
//    StringBuffer digtalA = new StringBuffer();
//    StringBuffer digtalB = new StringBuffer();
//    StringBuffer param = new StringBuffer();
//    private boolean isChar = false;
//    int operateChar = 0;
//
//    Button[] buttons_num = new Button[11];
//    int[] btnId_num = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonDOT};
//
//    //放 + - * /的按钮
//    Button[] buttons_operate = new Button[4];
//    //+ - * /
//    int[] btnId_operate = {R.id.buttonPLUS, R.id.buttonSUB, R.id.buttonMUL, R.id.buttonDEV};
//    TextView resultText;
//    TextView operText;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //设置内容视图
//        setContentView(R.qq.main_layout);
//
//        //实例化对象
//        initView();
//        //绑定监听事件
//        //也可以在xml中这样写
//        //android:onClick="myClick"
//        myClick myClickClass = new myClick();
//        for (int i = 0; i < buttons_num.length; i++) {
//            buttons_num[i].setOnClickListener(myClickClass);
//        }
//        for (int i = 0; i < buttons_operate.length; i++) {
//            buttons_operate[i].setOnClickListener(myClickClass);
//        }
//
//        btnIs = this.findViewById(R.id.buttonIS);
//        btnIs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                digtalB = param;
//                //运算
//                if (isChar == true) {
//                    float a = Float.valueOf(digtalA.toString());
//                    float b = Float.valueOf(digtalB.toString());
//                    isChar = false;
//                    digtalA = new StringBuffer();
//                    digtalB = new StringBuffer();
//                    param = new StringBuffer();
//                    float result = 0.0f;
//                    switch (operateChar) {
//                        case 0:
//                            result = a + b;
//                            break;
//                        case 1:
//                            result = a - b;
//                            break;
//                        case 2:
//                            result = a * b;
//                            break;
//                        case 3:
//                            result = a / b;
//                            break;
//                    }
//                    operText.setText("=");
//                    param = new StringBuffer(result + "");
//                    resultText.setText(result + "");
//                }
//            }
//        });
//
//        btnDel = this.findViewById(R.id.buttonDEL);
//        btnDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //删除最后一个
//                if (!param.toString().equals("")) {
//                    param = param.deleteCharAt(param.length() - 1);
//                    resultText.setText(param.toString());
//                }
//            }
//        });
//
//        btnC = this.findViewById(R.id.buttonC);
//        btnC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //清零
//                isChar = false;
//                digtalA = new StringBuffer();
//                digtalB = new StringBuffer();
//                param = new StringBuffer();
//                resultText.setText("0");
//                operText.setText("");
//            }
//        });
//    }
//
//    void initView() {
//        for (int i = 0; i < btnId_num.length; i++) {
//            buttons_num[i] = this.findViewById(btnId_num[i]);
//        }
//        for (int i = 0; i < btnId_operate.length; i++) {
//            buttons_operate[i] = this.findViewById(btnId_operate[i]);
//        }
//        resultText = this.findViewById(R.id.textView);
//        operText = this.findViewById(R.id.showOperate);
//    }
//
//    class myClick implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.button0:
//                    param.append("0");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button1:
//                    param.append("1");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button2:
//                    param.append("2");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button3:
//                    param.append("3");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button4:
//                    param.append("4");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button5:
//                    param.append("5");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button6:
//                    param.append("6");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button7:
//                    param.append("7");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button8:
//                    param.append("8");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.button9:
//                    param.append("9");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.buttonDOT:
//                    param.append(".");
//                    resultText.setText(param.toString());
//                    break;
//                case R.id.buttonPLUS:
//                    //进行加运算
//                    digtalA = param;
//                    isChar = true;
//                    param = new StringBuffer();
//                    resultText.setText("");
//                    operText.setText("+");
//                    operateChar = 0;
//                    break;
//                case R.id.buttonSUB:
//                    //进行-运算
//                    digtalA = param;
//                    isChar = true;
//                    param = new StringBuffer();
//                    resultText.setText("");
//                    operText.setText("-");
//                    operateChar = 1;
//                    break;
//                case R.id.buttonMUL:
//                    //进行*运算
//                    digtalA = param;
//                    isChar = true;
//                    param = new StringBuffer();
//                    resultText.setText("");
//                    operText.setText("*");
//                    operateChar = 2;
//                    break;
//                case R.id.buttonDEV:
//                    //进行/运算
//                    digtalA = param;
//                    isChar = true;
//                    param = new StringBuffer();
//                    resultText.setText("");
//                    operText.setText("÷");
//                    operateChar = 3;
//                    break;
//            }
//        }
//    }

}
