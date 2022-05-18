package com.example.ptitonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Ketquabaithi;
import com.example.ptitonline.models.Nguoidung;
import com.example.ptitonline.models.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    private TextView resultThongbao;
    private Button resultBtnTrangchu, resultBtnForward;
    private int baithiId, diem;
    private String monhoc, baithi;
    private boolean isBaithi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        if (isBaithi) {
            updateKetquabaithi();
            resultBtnForward.setText("Các bài thi đã làm");
            resultBtnForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ResultActivity.this, MyTestResultsActivity.class));
                    finish();
                }
            });
        } else {
            updateDiemluyentap();
            resultBtnForward.setText("Bảng xếp hạng");
            resultBtnForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ResultActivity.this, ScoreboardActivity.class));
                    finish();
                }
            });
        }

        resultBtnTrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void updateKetquabaithi() {
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(ResultActivity.this);
        Ketquabaithi tempKetquabaithi = new Ketquabaithi();
        tempKetquabaithi.setDiem(diem);
        tempKetquabaithi.setTblbaithiid(baithiId);
        tempKetquabaithi.setTblsinhvienid(user.getID());

        ApiService.apiService.postTestResult(tempKetquabaithi).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean isFulfilled = (Boolean) response.body();
                    if (isFulfilled)
                        Toast.makeText(ResultActivity.this, "Lưu kết quả làm bài thành công!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ResultActivity.this, "Chưa lưu được kết quả!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ResultActivity.this, "Query không thành công!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(ResultActivity.this, "Có gì đó không đúng ở đây!", Toast.LENGTH_SHORT).show();
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
                } else {
                    Toast.makeText(ResultActivity.this, "Có lỗi trong quá trình lưu điểm!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Nguoidung> call, Throwable t) {
                Toast.makeText(ResultActivity.this, "Có gì đó không đúng ở đây!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        resultThongbao = findViewById(R.id.resultThongbao);
        resultBtnTrangchu = findViewById(R.id.resultBtnTrangchu);
        resultBtnForward = findViewById(R.id.resultBtnForward);

        Intent intent = getIntent();
        isBaithi = intent.getBooleanExtra("isBaithi", false);
        diem = intent.getIntExtra("diem", 0);
        if (isBaithi) {
            baithi = intent.getStringExtra("baithi");
            baithiId = intent.getIntExtra("baithiId", 0);
            resultThongbao.setText("Chúc mừng bạn đã hoàn thành bài thi: " + baithi + " và được " + diem + " điểm!");
        } else {
            monhoc = intent.getStringExtra("monhoc");
            resultThongbao.setText("Chúc mừng bạn đã hoàn thành bài Quiz môn " + monhoc + " và được " + diem + " điểm!");
        }
    }
}