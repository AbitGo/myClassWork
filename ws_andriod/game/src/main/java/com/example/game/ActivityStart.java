package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityStart extends AppCompatActivity {
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        initView();
    }
    void initView(){
        editText = findViewById(R.id.roomId);
        button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityStart.this,ActivityPlay.class);
                String roomId = editText.getText().toString();
                intent.putExtra("roomId",roomId);
                if(roomId.equals("")){
                    Toast.makeText(ActivityStart.this,"房间号不能为空",Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });
    }
}
