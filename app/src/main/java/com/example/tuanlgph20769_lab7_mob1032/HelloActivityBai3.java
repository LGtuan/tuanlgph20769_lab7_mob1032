package com.example.tuanlgph20769_lab7_mob1032;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HelloActivityBai3 extends AppCompatActivity {

    private TextView txt_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello);

        initView();

        Intent intent = getIntent();

        String data = intent.getExtras().getString("data");

        txt_content.setText(data);
    }

    public void initView(){
        txt_content = findViewById(R.id.text_hello_bai3);
    }
}
