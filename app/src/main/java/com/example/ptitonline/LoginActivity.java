package com.example.ptitonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Nguoidung;
import com.example.ptitonline.models.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText taikhoan;
    private EditText matkhau;
    private Button btnDangnhap;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPref sharedPref = SharedPref.getInstance();
        sharedPref.clearSharedPref(LoginActivity.this);
        if (sharedPref.getUser(LoginActivity.this) != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        taikhoan = findViewById(R.id.taikhoan);
        matkhau = findViewById(R.id.matkhau);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiDangnhap();
//                Nguoidung user = new Nguoidung();
//                user.setID(42);
//                user.setHoten("huyen");
//                SharedPref sharedPref = SharedPref.getInstance();
//                sharedPref.setUser(LoginActivity.this, user);
//                if (sharedPref.getUser(LoginActivity.this) != null) {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
//                }
            }
        });
    }

    private void apiDangnhap() {
        String tk = taikhoan.getText().toString();
        String mk = matkhau.getText().toString();
        Nguoidung user = new Nguoidung();
        user.setUsername(tk);
        user.setPassword(mk);

        ApiService.apiService.login(user).enqueue(new Callback<Nguoidung>() {
            @Override
            public void onResponse(Call<Nguoidung> call, Response<Nguoidung> response) {
                if (response.isSuccessful()) {
                    Nguoidung user = (Nguoidung) response.body();
                    if (user == null) {
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPref sharedPref = SharedPref.getInstance();
                        sharedPref.setUser(LoginActivity.this, user);
                        if (sharedPref.getUser(LoginActivity.this) != null) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Nguoidung> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Có gì đó không đúng!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}