package com.example.ptitonline.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ptitonline.QuizActivity;
import com.example.ptitonline.R;
import com.example.ptitonline.ScoreboardActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LuyentapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LuyentapFragment extends Fragment {
    private Button luyentapBtnLaptrinhweb, luyentapBtnHethongphantan, luyentapBtnMangmaytinh;
    private ImageView luyentapScoreboard;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LuyentapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeLuyentapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LuyentapFragment newInstance(String param1, String param2) {
        LuyentapFragment fragment = new LuyentapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luyentap, container, false);
        initView(view);

        luyentapBtnLaptrinhweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                intent.putExtra("monhoc", "Lập trình web");
                intent.putExtra("monhocId", 1);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });

        luyentapBtnHethongphantan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                intent.putExtra("monhoc", "Hệ thống phân tán");
                intent.putExtra("monhocId", 2);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });

        luyentapBtnMangmaytinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                intent.putExtra("monhoc", "Mạng máy tính");
                intent.putExtra("monhocId", 3);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });

        luyentapScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ScoreboardActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });

        return view;
    }

    private void initView(View view) {
        luyentapBtnLaptrinhweb = view.findViewById(R.id.luyentapBtnLaptrinhweb);
        luyentapBtnHethongphantan = view.findViewById(R.id.luyentapBtnHethongphantan);
        luyentapBtnMangmaytinh = view.findViewById(R.id.luyentapBtnMangmaytinh);
        luyentapScoreboard = view.findViewById(R.id.luyentapScoreboard);
    }
}