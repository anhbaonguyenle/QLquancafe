package com.example.qlquancafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.ban) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new BanFragment()).commit();
                } else if (itemId == R.id.thucdon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ThucDonFragment()).commit();
                } else if (itemId == R.id.tinhtrang) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new TinhTrangFragment()).commit();
                } else if (itemId == R.id.hoadon) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HoaDonFragment()).commit();
                }
                return true;
            }
        });
    }
}