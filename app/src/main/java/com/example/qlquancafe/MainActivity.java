package com.example.qlquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editsdt, editmatkau;
    Button btnlogin, btnsignup;
    DBConnect DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editsdt = (EditText) findViewById(R.id.loginsdt);
        editmatkau = (EditText) findViewById(R.id.loginpass);
        btnlogin = (Button) findViewById(R.id.btbreturn);
        btnsignup = (Button) findViewById(R.id.btntaotai);
        DB = new DBConnect(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usersdt = editsdt.getText().toString();
                String userpass = editmatkau.getText().toString();
                if (usersdt.equals("") || userpass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hãy điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = DB.checkPassword(usersdt, userpass);
                    if(checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

    }
}