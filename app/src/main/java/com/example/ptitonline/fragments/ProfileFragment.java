package com.example.ptitonline.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptitonline.LoginActivity;
import com.example.ptitonline.MainActivity;
import com.example.ptitonline.MyTestResultsActivity;
import com.example.ptitonline.R;
import com.example.ptitonline.ResultActivity;
import com.example.ptitonline.api.ApiService;
import com.example.ptitonline.models.Nguoidung;
import com.example.ptitonline.models.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private EditText profileUsername, profileHoten, profileDiachi, profileEmail, profileDienthoai, profileDiemluyentap, profileVaitro, profileMatkhau, profileMatkhaumoi;
    private Button profileBtnCapnhat, profileBtnCapnhatMatkhau, profileBtnDangxuat;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(view);
        initData();

        profileBtnCapnhatMatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePasswordHandler();
            }
        });
        profileBtnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserHandler();
            }
        });

        profileBtnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref sharedPref = SharedPref.getInstance();
                sharedPref.clearSharedPref(getContext());
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        return view;
    }

    private void updatePasswordHandler() {
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(getContext());
        String matkhau = profileMatkhau.getText().toString();
        String matkhaumoi = profileMatkhaumoi.getText().toString();
        try {
            if (matkhau.isEmpty() || matkhaumoi.isEmpty()) throw new Exception();
            if (!matkhau.equals(user.getPassword())) throw new Exception();

            Nguoidung tempUser = new Nguoidung();
            tempUser.setHoten(user.getHoten());
            tempUser.setDiachi(user.getDiachi());
            tempUser.setEmail(user.getEmail());
            tempUser.setDienthoai(user.getDienthoai());
            tempUser.setPassword(matkhaumoi);

            ApiService.apiService.updateUser(user.getID(), tempUser).enqueue(new Callback<Nguoidung>() {
                @Override
                public void onResponse(Call<Nguoidung> call, Response<Nguoidung> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Nguoidung> call, Throwable t) {
                    Toast.makeText(getContext(), "Server đang lỗi, thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getContext(), "Nhập mật khẩu hiện tại và mật khẩu mới!", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateUserHandler() {
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(getContext());
        String hoten = profileHoten.getText().toString();
        String diachi = profileDiachi.getText().toString();
        String email = profileEmail.getText().toString();
        String dienthoai = profileDienthoai.getText().toString();

        try {
            Nguoidung tempUser = new Nguoidung();
            tempUser.setHoten(hoten.isEmpty() ? user.getHoten() : hoten);
            tempUser.setDiachi(diachi.isEmpty() ? user.getDiachi() : diachi);
            tempUser.setEmail(email.isEmpty() ? user.getEmail() : email);
            tempUser.setDienthoai(dienthoai.isEmpty() ? user.getDienthoai() : dienthoai);

            ApiService.apiService.updateUser(user.getID(), tempUser).enqueue(new Callback<Nguoidung>() {
                @Override
                public void onResponse(Call<Nguoidung> call, Response<Nguoidung> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Cập nhật thông tin người dùng thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Nguoidung> call, Throwable t) {
                    Toast.makeText(getContext(), "Server đang lỗi, thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getContext(), "Kiểm tra lại thông tin nhập vào!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {
        SharedPref sharedPref = SharedPref.getInstance();
        Nguoidung user = sharedPref.getUser(getContext());
        profileUsername.setText(user.getUsername());
        profileHoten.setText(user.getHoten());
        profileDiachi.setText(user.getDiachi());
        profileEmail.setText(user.getEmail());
        profileDienthoai.setText(user.getDienthoai());
        profileDiemluyentap.setText(user.getDiemluyentap() + "");
        profileVaitro.setText(user.getVaitro());
    }

    private void initView(View view) {
        profileBtnCapnhat = view.findViewById(R.id.profileBtnCapnhat);
        profileBtnDangxuat = view.findViewById(R.id.profileBtnDangxuat);
        profileBtnCapnhatMatkhau = view.findViewById(R.id.profileBtnCapnhatMatkhau);
        profileUsername = view.findViewById(R.id.profileUsername);
        profileHoten = view.findViewById(R.id.profileHoten);
        profileDiachi = view.findViewById(R.id.profileDiachi);
        profileDienthoai = view.findViewById(R.id.profileDienthoai);
        profileEmail = view.findViewById(R.id.profileEmail);
        profileDiemluyentap = view.findViewById(R.id.profileDiemluyentap);
        profileVaitro = view.findViewById(R.id.profileVaitro);
        profileMatkhau = view.findViewById(R.id.profileMatkhau);
        profileMatkhaumoi = view.findViewById(R.id.profileMatkhaumoi);
    }
}