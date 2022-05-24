package com.example.ptitonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Cauhoi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    private ImageView testBackicon, testHinhanh;
    private List<Button> btnAnswers;
    private TextView testTenbaithi, testCauhoi, testDiem, testCauhoihientai;
    private Button testBtnCautraloi1, testBtnCautraloi2, testBtnCautraloi3, testBtnCautraloi4;
    private String varBaithi;
    private int varBaithiId, varCauhoihientai, varDiem, curAnswer;
    private List<Cauhoi> listCauhoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        getQuestions();

        testBackicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        testBtnCautraloi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(testBtnCautraloi1, 0);
            }
        });
        testBtnCautraloi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(testBtnCautraloi2, 1);
            }
        });
        testBtnCautraloi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(testBtnCautraloi3, 2);
            }
        });
        testBtnCautraloi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(testBtnCautraloi4, 3);
            }
        });
    }

    private void checkAnswer(Button btn, int btnID) {
        for (Button i : btnAnswers) {
            i.setClickable(false);
        }
        btnAnswers.get(curAnswer).setTextColor(Color.parseColor("#4fe067"));
        if (btnID != curAnswer) {
            btn.setTextColor(Color.parseColor("#e85d5d"));
            Toast.makeText(TestActivity.this, ":(", Toast.LENGTH_SHORT).show();
        } else {
            varDiem++;
            testDiem.setText("Điểm: " + varDiem);
            Toast.makeText(TestActivity.this, ":)", Toast.LENGTH_SHORT).show();
        }

        varCauhoihientai++;
        new android.os.Handler(Looper.getMainLooper()).
                postDelayed(
                        new Runnable() {
                            public void run() {
                                displayNextQuestion();
                            }
                        },
                        2000);
    }

    private void getQuestions() {
        ApiService.apiService.getTestQuestions(varBaithiId).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {
                if (response.isSuccessful()) {
                    listCauhoi = (List<Cauhoi>) response.body();
                    displayNextQuestion();
                }
            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {
                finish();
                Toast.makeText(TestActivity.this, "Server đang lỗi, thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayNextQuestion() {
        if (varCauhoihientai == listCauhoi.size()) {
            Intent intent = new Intent(TestActivity.this, ResultActivity.class);
            intent.putExtra("isBaithi", true);
            intent.putExtra("diem", varDiem);
            intent.putExtra("baithi", varBaithi);
            intent.putExtra("baithiId", varBaithiId);
            startActivity(intent);
            finish();
        } else {
            ArrayList<String> answers = new ArrayList<>();
            answers.add(listCauhoi.get(varCauhoihientai).getCautraloidung());
            answers.add(listCauhoi.get(varCauhoihientai).getCautraloisai1());
            answers.add(listCauhoi.get(varCauhoihientai).getCautraloisai2());
            answers.add(listCauhoi.get(varCauhoihientai).getCautraloisai3());
            Collections.shuffle(answers);
            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i).equals(listCauhoi.get(varCauhoihientai).getCautraloidung()))
                    curAnswer = i;
            }

            for (Button i : btnAnswers) {
                i.setClickable(true);
            }
            testBtnCautraloi1.setText(answers.get(0));
            testBtnCautraloi1.setTextColor(Color.WHITE);
            testBtnCautraloi2.setText(answers.get(1));
            testBtnCautraloi2.setTextColor(Color.WHITE);
            testBtnCautraloi3.setText(answers.get(2));
            testBtnCautraloi3.setTextColor(Color.WHITE);
            testBtnCautraloi4.setText(answers.get(3));
            testBtnCautraloi4.setTextColor(Color.WHITE);
            testCauhoi.setText(listCauhoi.get(varCauhoihientai).getCauhoi());

            if (!listCauhoi.get(varCauhoihientai).getHinhanh().isEmpty()) {
                testHinhanh.getLayoutParams().height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                testHinhanh.setVisibility(View.VISIBLE);
                new TestActivity.DownloadImageTask(testHinhanh)
                        .execute(listCauhoi.get(varCauhoihientai).getHinhanh());
            } else {
                testHinhanh.getLayoutParams().height = 0;
                testHinhanh.setVisibility(View.INVISIBLE);
            }

            int temp = varCauhoihientai + 1;
            testCauhoihientai.setText("Câu hỏi hiện tại: " + temp + "/" + listCauhoi.size());
        }
    }

    private void initView() {
        testBackicon = findViewById(R.id.testBackicon);
        testHinhanh = findViewById(R.id.testHinhanh);
        testTenbaithi = findViewById(R.id.testTenbaithi);
        testCauhoi = findViewById(R.id.testCauhoi);
        testDiem = findViewById(R.id.testDiem);
        testCauhoihientai = findViewById(R.id.testCauhoihientai);
        testBtnCautraloi1 = findViewById(R.id.testBtnCautraloi1);
        testBtnCautraloi2 = findViewById(R.id.testBtnCautraloi2);
        testBtnCautraloi3 = findViewById(R.id.testBtnCautraloi3);
        testBtnCautraloi4 = findViewById(R.id.testBtnCautraloi4);

        btnAnswers = new ArrayList<>();
        btnAnswers.add(testBtnCautraloi1);
        btnAnswers.add(testBtnCautraloi2);
        btnAnswers.add(testBtnCautraloi3);
        btnAnswers.add(testBtnCautraloi4);

        Intent intent = getIntent();
        varBaithi = intent.getStringExtra("baithi");
        varBaithiId = intent.getIntExtra("baithiId", 0);
        varCauhoihientai = 0;
        varDiem = 0;

        testDiem.setText("Điểm: 0");
        testTenbaithi.setText(varBaithi);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}