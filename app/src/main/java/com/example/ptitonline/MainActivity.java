package com.example.ptitonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptitonline.adapters.BottomNavViewpagerAdapter;
import com.example.ptitonline.models.Nguoidung;
import com.example.ptitonline.models.SharedPref;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private TextView mainHello;
    private ViewPager mainViewpager;
    private BottomNavigationView mainBottomnav;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(MainActivity.this);
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(MainActivity.this);
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mainHello = findViewById(R.id.mainHello);
        mainViewpager = findViewById(R.id.mainViewpager);
        mainBottomnav = findViewById(R.id.mainBottomnav);

        BottomNavViewpagerAdapter adapter = new BottomNavViewpagerAdapter(getSupportFragmentManager(), 3);
        mainViewpager.setAdapter(adapter);

        mainViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mainBottomnav.getMenu().findItem(R.id.menuLuyentap);
                        break;
                    case 1:
                        mainBottomnav.getMenu().findItem(R.id.menuBaithi);
                        break;
                    case 2:
                        mainBottomnav.getMenu().findItem(R.id.menuProfile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mainBottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuLuyentap:
                        mainViewpager.setCurrentItem(0);
                        break;
                    case R.id.menuBaithi:
                        mainViewpager.setCurrentItem(1);
                        break;
                    case R.id.menuProfile:
                        mainViewpager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(this);
        mainHello.setText("Xin chào, " + user.getHoten());
    }
}