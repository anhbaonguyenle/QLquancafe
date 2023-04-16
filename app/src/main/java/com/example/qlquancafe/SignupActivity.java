package com.example.qlquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText sdt,pass1,pass2;
    Button tao,ve;
    DBConnect DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sdt = (EditText) findViewById(R.id.loginsdt);
        pass1 = (EditText) findViewById(R.id.loginpass);
        pass2 = (EditText) findViewById(R.id.loginpass2);
        tao = (Button) findViewById(R.id.btntaotai);
        ve = (Button) findViewById(R.id.btbreturn);
        DB = new DBConnect(this);
        tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usersdt = String.valueOf(sdt);
                String userpass = pass1.getText().toString();
                String userpass2 = pass2.getText().toString();
                if (usersdt.equals("") || userpass.equals("") || userpass2.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hãy điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!userpass.equals(userpass2)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checksdt = DB.checksdt(usersdt);
                    if(checksdt==false){
                        Boolean insert = DB.insertData(usersdt,userpass);
                        if (insert) {
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Số điện thoại đã được dùng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}