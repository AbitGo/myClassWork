package com.example.thirdlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    //清零，删除上一字符，等于
    Button btnC, btnDel, btnIs;
    //单一数据
    StringBuffer paramOne = new StringBuffer();

    Button[] buttons_num = new Button[11];
    int[] btnId_num = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonDOT};

    //放 + - * /的按钮
    Button[] buttons_operate = new Button[4];
    //+ - * /
    int[] btnId_operate = {R.id.buttonPLUS, R.id.buttonSUB, R.id.buttonMUL, R.id.buttonDEV};
    TextView showResult;
    TextView showAll;



    LinkedList<InnerOperateChar> linkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置内容视图
        setContentView(R.layout.main_layout);
        linkedList = new LinkedList();

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
                linkedList.add(new InnerOperateChar(paramOne.toString()));
                paramOne = new StringBuffer();
                showAll.setText(AllToString(linkedList));

                OperateSu1 operateChar = new OperateSu1();
                LinkedList<InnerOperateChar> suffer = operateChar.getrp(linkedList);
                float result = operateChar.calrp(suffer);
                showResult.setText(result + "");
            }
        });

        btnDel = this.findViewById(R.id.buttonDEL);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除最后一个
                //1.如果paramOne还有数据就进行删除
                //1.1 并且showResult需要同步删除

                //2.如果paramOne没数据，提取linkedList最后一个
                //2.1 如果是运算符，直接linkedList移除最后一个
                //2.2 如果是字符串，则将paramOne赋值为linkedList最后一个元素，并同步showResult
                //2.3 同步showAll

                //避免闪退
                if (linkedList.size() == 0 && paramOne.length() == 0) {
                    return;
                }
                if (paramOne.length() != 0) {
                    paramOne = new StringBuffer(paramOne.subSequence(0, paramOne.length() - 1));
                    showResult.setText(paramOne.toString());
                } else {
                    //如果是运算符的话，不需要任何操作
                    InnerOperateChar lastElem = linkedList.removeLast();
                    if (lastElem.isNum()) {
                        String lastNum = lastElem.getNumber();
                        paramOne = new StringBuffer(lastNum.substring(0, lastNum.length() - 1));
                        showResult.setText(paramOne.toString());
                    } else {
                        ;
                    }
                    showAll.setText(AllToString(linkedList));
                }
            }
        });

        btnC = this.findViewById(R.id.buttonC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清零
                //1.paramOne清零
                //2.showAll清零
                //3.showResult清零
                //4.linkedList清空
                paramOne = new StringBuffer();
                showAll.setText("");
                showResult.setText("");
                linkedList.clear();
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
        showAll = this.findViewById(R.id.showAll);
        showResult = this.findViewById(R.id.showResult);
    }

    class myClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button0:
                    //上端不用改变，只有进行运算的时候入队即可
                    paramOne.append("0");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button1:
                    paramOne.append("1");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button2:
                    paramOne.append("2");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button3:
                    paramOne.append("3");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button4:
                    paramOne.append("4");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button5:
                    paramOne.append("5");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button6:
                    paramOne.append("6");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button7:
                    paramOne.append("7");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button8:
                    paramOne.append("8");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.button9:
                    paramOne.append("9");
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.buttonDOT:
                    //如果出现再没数字的情况下直接按下.则直接变为0. 然后再进行后续操作
                    if (paramOne.length() == 0) {
                        paramOne.append("0.");
                    } else {
                        //如果出现1.1 然后还想点. 则不生效否则添加
                        if (paramOne.toString().contains(".")) {
                            ;
                        } else {
                            paramOne.append(".");
                        }
                    }
                    showResult.setText(paramOne.toString());
                    break;
                case R.id.buttonPLUS:
                    //进行加运算
                    //1.需要paramOne入队
                    //2.paramOne清空
                    //3.操作符入队
                    //4.showAll显示
                    //5.showResult清空

                    //以免造成 1.刚开始就使用运算符 2.出现两次运算
                    if (paramOne.length()!=0) {
                        //以免造成 12. - 12得现象
                        if (paramOne.charAt(paramOne.length() - 1) == '.') {
                            paramOne = new StringBuffer(paramOne.subSequence(0, paramOne.length() - 1));
                        }
                        linkedList.add(new InnerOperateChar(paramOne.toString()));
                        paramOne = new StringBuffer();
                        linkedList.add(new InnerOperateChar('+'));
                        showAll.setText(AllToString(linkedList));
                        showResult.setText("");
                    }
                    break;
                case R.id.buttonSUB:
                    //进行-运算
                    //如果paramOne 长度不为0的话，那就是要进行 - 减操作
                    if (paramOne.length() != 0) {
                        if (paramOne.charAt(paramOne.length() - 1) == '.') {
                            paramOne = new StringBuffer(paramOne.subSequence(0, paramOne.length() - 1));
                        }
                        linkedList.add(new InnerOperateChar(paramOne.toString()));
                        paramOne = new StringBuffer();
                        linkedList.add(new InnerOperateChar('-'));
                        showAll.setText(AllToString(linkedList));
                        showResult.setText("");
                    } else {
                        paramOne.append("-");
                        showResult.setText(paramOne.toString());
                    }
                    break;
                case R.id.buttonMUL:
                    //进行*运算
                    if (paramOne.length()!=0) {
                        if (paramOne.charAt(paramOne.length() - 1) == '.') {
                            paramOne = new StringBuffer(paramOne.subSequence(0, paramOne.length() - 1));
                        }
                        linkedList.add(new InnerOperateChar(paramOne.toString()));
                        paramOne = new StringBuffer();
                        linkedList.add(new InnerOperateChar('*'));
                        showAll.setText(AllToString(linkedList));
                        showResult.setText("");
                    }
                    break;
                case R.id.buttonDEV:
                    //进行/运算
                    if (paramOne.length()!=0) {
                        if (paramOne.charAt(paramOne.length() - 1) == '.') {
                            paramOne = new StringBuffer(paramOne.subSequence(0, paramOne.length() - 1));
                        }
                        linkedList.add(new InnerOperateChar(paramOne.toString()));
                        paramOne = new StringBuffer();
                        linkedList.add(new InnerOperateChar('/'));
                        showAll.setText(AllToString(linkedList));
                        showResult.setText("");
                    }
                    break;
            }
        }
    }

    public String AllToString(LinkedList<InnerOperateChar> s) {
        String result = "";
        for (InnerOperateChar innerOperateChar : s) {
            if (innerOperateChar.isNum()) {
                result += innerOperateChar.getNumber();
            } else {
                result += " " + innerOperateChar.getOperater() + " ";
            }
        }
        return result;
    }

}
