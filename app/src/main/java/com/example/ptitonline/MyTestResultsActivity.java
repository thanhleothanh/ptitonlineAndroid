package com.example.ptitonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptitonline.adapters.KetquabaithiAdapter;
import com.example.ptitonline.adapters.ScoreboardAdapter;
import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Ketquabaithi;
import com.example.ptitonline.models.Nguoidung;
import com.example.ptitonline.models.SharedPref;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTestResultsActivity extends AppCompatActivity {
    private RecyclerView mytestresultsRecycleview;
    private KetquabaithiAdapter ketquabaithiAdapter;
    private ImageView mytestresultsBackicon;
    private List<Ketquabaithi> ketquabaithiList;
    private TextView mytestresultsInfo;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test_results);
        mytestresultsRecycleview = findViewById(R.id.mytestresultsRecycleview);
        mytestresultsInfo = findViewById(R.id.mytestresultsInfo);
        ketquabaithiAdapter = new KetquabaithiAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mytestresultsRecycleview.setAdapter(ketquabaithiAdapter);
        mytestresultsRecycleview.setLayoutManager(manager);
        apiKetquabaithi();

        mytestresultsBackicon = findViewById(R.id.mytestresultsBackicon);
        mytestresultsBackicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void apiKetquabaithi() {
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(this);
        ApiService.apiService.getMyTestResults(user.getID()).enqueue(new Callback<List<Ketquabaithi>>() {
            @Override
            public void onResponse(Call<List<Ketquabaithi>> call, Response<List<Ketquabaithi>> response) {
                if (response.isSuccessful()) {
                    ketquabaithiList = (List<Ketquabaithi>) response.body();
                    if (ketquabaithiList.size() == 0)
                        mytestresultsInfo.setVisibility(View.VISIBLE);
                    else {
                        mytestresultsInfo.setVisibility(View.GONE);
                        ketquabaithiAdapter.setList(ketquabaithiList);
                    }
                    ketquabaithiAdapter.setList(ketquabaithiList);
                }
            }

            @Override
            public void onFailure(Call<List<Ketquabaithi>> call, Throwable t) {
                Toast.makeText(MyTestResultsActivity.this, "Có gì đó không đúng ở đây!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}