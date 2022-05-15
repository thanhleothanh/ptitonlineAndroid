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
import android.widget.RelativeLayout;
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

public class QuizActivity extends AppCompatActivity {
    private ImageView backicon, hinhanh;
    private List<Button> btnAnswers;
    private TextView monhoc, cauhoi, diem, cauhoihientai;
    private Button btnCautraloi1, btnCautraloi2, btnCautraloi3, btnCautraloi4;
    private String varMonhoc;
    private int varMonhocId, varCauhoihientai, varDiem, curAnswer;
    private List<Cauhoi> listCauhoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();
        getQuestions();

        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCautraloi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(btnCautraloi1, 0);
            }
        });
        btnCautraloi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(btnCautraloi2, 1);
            }
        });
        btnCautraloi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(btnCautraloi3, 2);
            }
        });
        btnCautraloi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(btnCautraloi4, 3);
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
            Toast.makeText(QuizActivity.this, ":(", Toast.LENGTH_SHORT).show();
        } else {
            varDiem++;
            diem.setText("Điểm: " + varDiem);
            Toast.makeText(QuizActivity.this, ":)", Toast.LENGTH_SHORT).show();
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
        ApiService.apiService.getQuestions(varMonhocId).enqueue(new Callback<List<Cauhoi>>() {
            @Override
            public void onResponse(Call<List<Cauhoi>> call, Response<List<Cauhoi>> response) {
                if (response.isSuccessful()) {
                    listCauhoi = (List<Cauhoi>) response.body();
                    displayNextQuestion();
                }
            }

            @Override
            public void onFailure(Call<List<Cauhoi>> call, Throwable t) {
                Toast.makeText(QuizActivity.this, "Có gì đó không đúng!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayNextQuestion() {
        if (varCauhoihientai == listCauhoi.size()) {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("diem", varDiem);
            intent.putExtra("monhoc", varMonhoc);
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
            btnCautraloi1.setText(answers.get(0));
            btnCautraloi1.setTextColor(Color.WHITE);
            btnCautraloi2.setText(answers.get(1));
            btnCautraloi2.setTextColor(Color.WHITE);
            btnCautraloi3.setText(answers.get(2));
            btnCautraloi3.setTextColor(Color.WHITE);
            btnCautraloi4.setText(answers.get(3));
            btnCautraloi4.setTextColor(Color.WHITE);
            cauhoi.setText(listCauhoi.get(varCauhoihientai).getCauhoi());

            if (!listCauhoi.get(varCauhoihientai).getHinhanh().isEmpty()) {
                hinhanh.getLayoutParams().height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                hinhanh.setVisibility(View.VISIBLE);
                new DownloadImageTask(hinhanh)
                        .execute(listCauhoi.get(varCauhoihientai).getHinhanh());
            } else {
                hinhanh.getLayoutParams().height = 0;
                hinhanh.setVisibility(View.INVISIBLE);
            }

            int temp = varCauhoihientai + 1;
            cauhoihientai.setText("Câu hỏi hiện tại: " + temp + "/" + listCauhoi.size());
        }
    }

    private void initView() {
        backicon = findViewById(R.id.backiconQuiz);
        hinhanh = findViewById(R.id.hinhanh);
        monhoc = findViewById(R.id.monhoc);
        cauhoi = findViewById(R.id.cauhoi);
        diem = findViewById(R.id.diem);
        cauhoihientai = findViewById(R.id.cauhoihientai);
        btnCautraloi1 = findViewById(R.id.btnCautraloi1);
        btnCautraloi2 = findViewById(R.id.btnCautraloi2);
        btnCautraloi3 = findViewById(R.id.btnCautraloi3);
        btnCautraloi4 = findViewById(R.id.btnCautraloi4);
        btnAnswers = new ArrayList<>();
        btnAnswers.add(btnCautraloi1);
        btnAnswers.add(btnCautraloi2);
        btnAnswers.add(btnCautraloi3);
        btnAnswers.add(btnCautraloi4);

        Intent intent = getIntent();
        varMonhoc = intent.getStringExtra("monhoc");
        varMonhocId = intent.getIntExtra("monhocId", 1);
        varCauhoihientai = 0;
        varDiem = 0;

        diem.setText("Điểm: 0");
        monhoc.setText(varMonhoc);
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
