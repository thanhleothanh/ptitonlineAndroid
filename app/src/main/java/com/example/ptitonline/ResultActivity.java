package com.example.ptitonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Nguoidung;
import com.example.ptitonline.models.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    private TextView thongbao;
    private Button btnTrangchu, btnBangxephang;
    private int diem;
    private String monhoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        updateDiemluyentap();

        btnTrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnBangxephang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, ScoreboardActivity.class));
                finish();
            }
        });
    }

    private void updateDiemluyentap() {
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(ResultActivity.this);
        Nguoidung tempUser = new Nguoidung();
        tempUser.setDiemluyentap(diem);
        ApiService.apiService.updateUser(user.getID(), tempUser).enqueue(new Callback<Nguoidung>() {
            @Override
            public void onResponse(Call<Nguoidung> call, Response<Nguoidung> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ResultActivity.this, "Cập nhật điểm thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Nguoidung> call, Throwable t) {
                Toast.makeText(ResultActivity.this, "Có gì đó không đúng ở đây!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        thongbao = findViewById(R.id.thongbao);
        btnTrangchu = findViewById(R.id.btnTrangchu);
        btnBangxephang = findViewById(R.id.btnBangxephang);

        Intent intent = getIntent();
        diem = intent.getIntExtra("diem", 0);
        monhoc = intent.getStringExtra("monhoc");
        thongbao.setText("Chúc mừng bạn đã hoàn thành bài Quiz môn " + monhoc + " và được " + diem + " điểm!");
    }
}