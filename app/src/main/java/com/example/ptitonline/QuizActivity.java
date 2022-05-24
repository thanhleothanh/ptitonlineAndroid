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
    private ImageView quizBackicon, quizHinhanh;
    private List<Button> btnAnswers;
    private TextView quizMonhoc, quizCauhoi, quizDiem, quizCauhoihientai;
    private Button quizBtnCautraloi1, quizBtnCautraloi2, quizBtnCautraloi3, quizBtnCautraloi4;
    private String varMonhoc;
    private int varMonhocId, varCauhoihientai, varDiem, curAnswer;
    private List<Cauhoi> listCauhoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();
        getQuestions();

        quizBackicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        quizBtnCautraloi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(quizBtnCautraloi1, 0);
            }
        });
        quizBtnCautraloi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(quizBtnCautraloi2, 1);
            }
        });
        quizBtnCautraloi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(quizBtnCautraloi3, 2);
            }
        });
        quizBtnCautraloi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(quizBtnCautraloi4, 3);
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
            quizDiem.setText("Điểm: " + varDiem);
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
                Toast.makeText(QuizActivity.this, "Server đang lỗi, thử lại sau!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void displayNextQuestion() {
        if (varCauhoihientai == listCauhoi.size()) {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("isBaithi", false);
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
            quizBtnCautraloi1.setText(answers.get(0));
            quizBtnCautraloi1.setTextColor(Color.WHITE);
            quizBtnCautraloi2.setText(answers.get(1));
            quizBtnCautraloi2.setTextColor(Color.WHITE);
            quizBtnCautraloi3.setText(answers.get(2));
            quizBtnCautraloi3.setTextColor(Color.WHITE);
            quizBtnCautraloi4.setText(answers.get(3));
            quizBtnCautraloi4.setTextColor(Color.WHITE);
            quizCauhoi.setText(listCauhoi.get(varCauhoihientai).getCauhoi());

            if (!listCauhoi.get(varCauhoihientai).getHinhanh().isEmpty()) {
                quizHinhanh.getLayoutParams().height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                quizHinhanh.setVisibility(View.VISIBLE);
                new DownloadImageTask(quizHinhanh)
                        .execute(listCauhoi.get(varCauhoihientai).getHinhanh());
            } else {
                quizHinhanh.getLayoutParams().height = 0;
                quizHinhanh.setVisibility(View.INVISIBLE);
            }

            int temp = varCauhoihientai + 1;
            quizCauhoihientai.setText("Câu hỏi hiện tại: " + temp + "/" + listCauhoi.size());
        }
    }

    private void initView() {
        quizBackicon = findViewById(R.id.quizBackicon);
        quizHinhanh = findViewById(R.id.quizHinhanh);
        quizMonhoc = findViewById(R.id.quizMonhoc);
        quizCauhoi = findViewById(R.id.quizCauhoi);
        quizDiem = findViewById(R.id.quizDiem);
        quizCauhoihientai = findViewById(R.id.quizCauhoihientai);
        quizBtnCautraloi1 = findViewById(R.id.quizBtnCautraloi1);
        quizBtnCautraloi2 = findViewById(R.id.quizBtnCautraloi2);
        quizBtnCautraloi3 = findViewById(R.id.quizBtnCautraloi3);
        quizBtnCautraloi4 = findViewById(R.id.quizBtnCautraloi4);

        btnAnswers = new ArrayList<>();
        btnAnswers.add(quizBtnCautraloi1);
        btnAnswers.add(quizBtnCautraloi2);
        btnAnswers.add(quizBtnCautraloi3);
        btnAnswers.add(quizBtnCautraloi4);

        Intent intent = getIntent();
        varMonhoc = intent.getStringExtra("monhoc");
        varMonhocId = intent.getIntExtra("monhocId", 1);
        varCauhoihientai = 0;
        varDiem = 0;

        quizDiem.setText("Điểm: 0");
        quizMonhoc.setText(varMonhoc);
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
