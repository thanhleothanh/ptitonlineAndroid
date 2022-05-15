package com.example.ptitonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Cauhoi;
import com.example.ptitonline.models.Nguoidung;
import com.example.ptitonline.models.SharedPref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnMonhoc1, btnMonhoc2, btnMonhoc3;
    private TextView hello;
    private ImageView signout, scoreboard;

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

        btnMonhoc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("monhoc", "Lập trình web");
                intent.putExtra("monhocId", 1);
                startActivity(intent);
            }
        });

        btnMonhoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("monhoc", "Hệ thống phân tán");
                intent.putExtra("monhocId", 2);
                startActivity(intent);
            }
        });

        btnMonhoc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("monhoc", "Mạng máy tính");
                intent.putExtra("monhocId", 3);
                startActivity(intent);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref sharedPref = SharedPref.getInstance();
                sharedPref.clearSharedPref(MainActivity.this);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScoreboardActivity.class));
            }
        });
    }

    private void initView() {
        btnMonhoc1 = findViewById(R.id.btnMonhoc1);
        btnMonhoc2 = findViewById(R.id.btnMonhoc2);
        btnMonhoc3 = findViewById(R.id.btnMonhoc3);
        hello = findViewById(R.id.hello);
        signout = findViewById(R.id.signout);
        scoreboard = findViewById(R.id.scoreboard);

        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(this);
        hello.setText("Xin chào, " + user.getHoten());
    }
}