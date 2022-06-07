package com.example.tuanlgph20769_lab7_mob1032;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Bai3 extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUser, edtPass;
    private CheckBox cbxRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        initView();

        btnLogin.setOnClickListener(this);

        List<Object> list = getData();
        edtUser.setText(list.get(0).toString());
        edtPass.setText(list.get(1).toString());
        cbxRemember.setChecked((Boolean) list.get(2));

    }

    public void saveData() {
        String user = edtUser.getText().toString();
        String pass = edtPass.getText().toString();

        if (user.equals("") || pass.equals("")) {
            Toast.makeText(this, "Enter all data", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = this.getSharedPreferences("MY_SHARED_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean remember = cbxRemember.isChecked();
        if (!remember) {
            editor.clear();
            editor.apply();
        }else{
            editor.putBoolean("remember", true);
            editor.putString("user", edtUser.getText().toString());
            editor.putString("pass", edtPass.getText().toString());

            editor.apply();

            Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(Bai3.this, HelloActivityBai3.class);
        intent.putExtra("data", "Hello " + edtUser.getText().toString());
        startActivity(intent);
    }

    public List<Object> getData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("MY_SHARED_PREFERENCES", MODE_PRIVATE);

        List<Object> list = new ArrayList<>();

        boolean remember = sharedPreferences.getBoolean("remember", false);

        String user = sharedPreferences.getString("user", "");
        String pass = sharedPreferences.getString("pass", "");

        if (user != null) list.add(user);
        if (pass != null) list.add(pass);
        list.add(remember);

        return list;
    }

    public void initView() {
        edtPass = findViewById(R.id.edt_pass_bai3);
        edtUser = findViewById(R.id.edt_user_bai3);
        cbxRemember = findViewById(R.id.cbx_remember_bai3);
        btnLogin = findViewById(R.id.btn_login_bai3);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_login_bai3) {
            saveData();
        }
    }

}