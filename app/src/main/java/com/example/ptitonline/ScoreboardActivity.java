package com.example.ptitonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptitonline.adapters.ScoreboardAdapter;
import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Nguoidung;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreboardActivity extends AppCompatActivity {
    private RecyclerView scoreboardRecycleview;
    private ScoreboardAdapter scoreboardAdapter;
    private ImageView scoreboardBackicon;
    private List<Nguoidung> nguoidungList;
    private TextView scoreboardInfo;
    private CardView scoreboardTablehead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        initView();

        scoreboardAdapter = new ScoreboardAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        scoreboardRecycleview.setAdapter(scoreboardAdapter);
        scoreboardRecycleview.setLayoutManager(manager);
        apiBangxepahang();

        scoreboardBackicon.setOnClickListener(new View.OnClickListener() {
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
                    nguoidungList = (List<Nguoidung>) response.body();
                    scoreboardAdapter.setList(nguoidungList);
                    scoreboardInfo.setVisibility(View.GONE);
                    scoreboardTablehead.setVisibility(View.VISIBLE);
                } else {
                    scoreboardInfo.setVisibility(View.VISIBLE);
                    scoreboardTablehead.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Nguoidung>> call, Throwable t) {
                Toast.makeText(ScoreboardActivity.this, "Server đang lỗi, thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        scoreboardRecycleview = findViewById(R.id.scoreboardRecycleView);
        scoreboardInfo = findViewById(R.id.scoreboardInfo);
        scoreboardTablehead = findViewById(R.id.scoreboardTablehead);
        scoreboardBackicon = findViewById(R.id.scoreboardBackicon);
    }


}