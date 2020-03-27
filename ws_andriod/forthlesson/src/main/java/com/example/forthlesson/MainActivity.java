package com.example.forthlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText edtTel,edtPwd;

    private Toast toast;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = edtTel.getText().toString();
                String pwd = edtPwd.getText().toString();
                if (tel.equals("123") && pwd.equals("123")) {
//                    //MainActivity / getApplicationContext()
//                    Toast.makeText(getApplicationContext(),R.string.login_ok,Toast.LENGTH_LONG).show();
                    showToast(R.mipmap.ok,"登陆成功");
                }else{
                    showToast(R.mipmap.ok,"密码错误");
                }
            }
        });
    }

    ////自定义Toast
    void showToast(int param,String info){
        toast = new Toast(getApplicationContext());
        imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(param);
        textView = new TextView(getApplicationContext());
        textView.setText(info);
        linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        toast.setView(linearLayout);

        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    void initView(){
        button = this.findViewById(R.id.btnLogin);
        edtTel = this.findViewById(R.id.EdtTel);
        edtPwd = this.findViewById(R.id.EdtPwd);
    }
}
