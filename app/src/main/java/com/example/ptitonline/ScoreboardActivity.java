package com.example.ptitonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ptitonline.adapters.ScoreboardAdapter;
import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Nguoidung;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreboardActivity extends AppCompatActivity {
    private RecyclerView bangxephangRecycleView;
    private ScoreboardAdapter scoreboardAdapter;
    private ImageView backicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        bangxephangRecycleView = findViewById(R.id.bangxephangRecycleView);
        scoreboardAdapter = new ScoreboardAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        bangxephangRecycleView.setAdapter(scoreboardAdapter);
        bangxephangRecycleView.setLayoutManager(manager);
        apiBangxepahang();


        backicon = findViewById(R.id.backiconScoreboard);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void apiBangxepahang() {
        ApiService.apiService.getScoreboard().enqueue(new Callback<List<Nguoidung>>() {
            @Override
            public void onResponse(Call<List<Nguoidung>> call, Response<List<Nguoidung>> response) {
                if (response.isSuccessful()) {
                    List<Nguoidung> list = (List<Nguoidung>) response.body();
                    scoreboardAdapter.setList(list);
                }
            }

            @Override
            public void onFailure(Call<List<Nguoidung>> call, Throwable t) {
                Toast.makeText(ScoreboardActivity.this, "Có gì đó không đúng ở đây!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}