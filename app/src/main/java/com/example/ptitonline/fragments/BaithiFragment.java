package com.example.ptitonline.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptitonline.LoginActivity;
import com.example.ptitonline.MainActivity;
import com.example.ptitonline.MyTestResultsActivity;
import com.example.ptitonline.QuizActivity;
import com.example.ptitonline.R;
import com.example.ptitonline.ScoreboardActivity;
import com.example.ptitonline.TestActivity;
import com.example.ptitonline.adapters.BaithiAdapter;
import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Baithi;
import com.example.ptitonline.models.Cauhoi;
import com.example.ptitonline.models.Ketquabaithi;
import com.example.ptitonline.models.Nguoidung;
import com.example.ptitonline.models.SharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaithiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaithiFragment extends Fragment implements BaithiAdapter.BaithiClickListener {
    private SwipeRefreshLayout baithiRefresh;
    private RecyclerView baithiRecycleview;
    private TextView baithiInfo;
    private BaithiAdapter baithiAdapter;
    private ImageView baithiDalam;
    private List<Baithi> baithiList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BaithiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeBaithiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaithiFragment newInstance(String param1, String param2) {
        BaithiFragment fragment = new BaithiFragment();
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
        View view = inflater.inflate(R.layout.fragment_baithi, container, false);
        initView(view);
        initData();

        baithiRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                baithiRefresh.setRefreshing(false);
            }
        });

        baithiDalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyTestResultsActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });

        return view;
    }

    private void initView(View view) {
        baithiRecycleview = view.findViewById(R.id.baithiRecycleview);
        baithiInfo = view.findViewById(R.id.baithiInfo);
        baithiRefresh = view.findViewById(R.id.baithiRefresh);
        baithiDalam = view.findViewById(R.id.baithiDalam);

        baithiAdapter = new BaithiAdapter();
        baithiAdapter.setBaithiClickListener(this);

        baithiRecycleview.setAdapter(baithiAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        baithiRecycleview.setLayoutManager(manager);
    }

    private void initData() {
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(getContext());

        ApiService.apiService.getTests(user.getID() + "").enqueue(new Callback<List<Baithi>>() {
            @Override
            public void onResponse(Call<List<Baithi>> call, Response<List<Baithi>> response) {
                if (response.isSuccessful()) {
                    baithiList = (List<Baithi>) response.body();
                    baithiAdapter.setList(baithiList);
                    if (baithiList.size() == 0)
                        baithiInfo.setVisibility(View.VISIBLE);
                    else {
                        baithiInfo.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Baithi>> call, Throwable t) {
                baithiInfo.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Server đang lỗi, không thể lấy được các bài thi, thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getTestResult(Baithi baithi, int baithiId, int userId) {
        ApiService.apiService.getTestResult(baithiId, userId).enqueue(new Callback<Ketquabaithi>() {
            @Override
            public void onResponse(Call<Ketquabaithi> call, Response<Ketquabaithi> response) {
                if (response.isSuccessful()) {
                    Ketquabaithi ketquabaithi = (Ketquabaithi) response.body();
                    if (ketquabaithi != null) {
                        Toast.makeText(getContext(), "Sinh viên đã làm bài thi này trước đó!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent intent = new Intent(getContext(), TestActivity.class);
                    intent.putExtra("baithiId", baithi.getID());
                    intent.putExtra("baithi", baithi.getTenbaithi());
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }
            }

            @Override
            public void onFailure(Call<Ketquabaithi> call, Throwable t) {
                Toast.makeText(getContext(), "Server đang lỗi, thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(getContext());
        Baithi baithi = baithiList.get(position);
        getTestResult(baithi, baithi.getID(), user.getID());
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
