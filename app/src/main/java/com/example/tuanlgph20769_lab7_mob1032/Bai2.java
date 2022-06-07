package com.example.tuanlgph20769_lab7_mob1032;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Bai2 extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTERNAL_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    public static final String MY_PATH = "data.txt";

    private TextView text_content;
    private EditText edt_data;
    private Button btn_read,btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        initView();

        btn_read.setOnClickListener(this);
        btn_write.setOnClickListener(this);
    }

    public void initView(){
        text_content = findViewById(R.id.txt_bai2);
        edt_data = findViewById(R.id.edt_bai2);
        btn_read = findViewById(R.id.btn_read_bai2);
        btn_write = findViewById(R.id.btn_write_bai2);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_read_bai2){
            text_content.setText(readData());
        }else if(v.getId() == R.id.btn_write_bai2){
            writeData();
        }
    }

    public void writeData(){
        String path = EXTERNAL_PATH + MY_PATH;

        File file = new File(path);
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);

            String data = edt_data.getText().toString();
            fos.write(data.getBytes());

            fos.close();

            Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show();
        }catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String readData(){
        String path = EXTERNAL_PATH + MY_PATH;
        String data = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            data = br.readLine();
            br.close();

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return data;
    }

}