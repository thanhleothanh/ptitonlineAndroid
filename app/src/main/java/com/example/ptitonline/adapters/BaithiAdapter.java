package com.example.ptitonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptitonline.R;
import com.example.ptitonline.models.Baithi;

import java.util.ArrayList;
import java.util.List;

public class BaithiAdapter extends RecyclerView.Adapter<BaithiAdapter.BaithiViewHolder> {
    private List<Baithi> list;
    private BaithiClickListener baithiClickListener;

    public BaithiAdapter() {
        list = new ArrayList<>();
    }

    public void setBaithiClickListener(BaithiClickListener baithiClickListener) {
        this.baithiClickListener = baithiClickListener;
    }

    public void setList(List<Baithi> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Baithi> getList() {
        return list;
    }

    @NonNull
    @Override
    public BaithiAdapter.BaithiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baithi, parent, false);
        return new BaithiAdapter.BaithiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaithiAdapter.BaithiViewHolder holder, int position) {
        Baithi baithi = list.get(position);
        if (baithi == null) return;
        holder.itemBaithiTenbaithi.setText("Tên bài thi: " + baithi.getTenbaithi());
        holder.itemBaithiMota.setText("Mô tả: " + baithi.getMota());
        holder.itemBaithiGiaovien.setText("Giáo viên: " + baithi.getGiaovien().getHoten());
        holder.itemBaithiMonhoc.setText("Môn học: " + baithi.getMonhoc().getTenmon());
        holder.itemBaithiImg.setImageResource(R.drawable.test);
//        int monhocID = baithi.getMonhoc().getID();
//        if (monhocID == 1) holder.itemBaithiImg.setImageResource(R.drawable.laptrinhweb);
//        else if (monhocID == 2) holder.itemBaithiImg.setImageResource(R.drawable.hethongphantan);
//        else holder.itemBaithiImg.setImageResource(R.drawable.mangmaytinh);
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public class BaithiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemBaithiTenbaithi, itemBaithiGiaovien, itemBaithiMota, itemBaithiMonhoc;
        private ImageFilterView itemBaithiImg;
        private Button itemBaithiBtnBatdau;

        public BaithiViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBaithiBtnBatdau = itemView.findViewById(R.id.itemBaithiBtnBatdau);
            itemBaithiTenbaithi = itemView.findViewById(R.id.itemBaithiTenbaithi);
            itemBaithiGiaovien = itemView.findViewById(R.id.itemBaithiGiaovien);
            itemBaithiMota = itemView.findViewById(R.id.itemBaithiMota);
            itemBaithiMonhoc = itemView.findViewById(R.id.itemBaithiMonhoc);
            itemBaithiImg = itemView.findViewById(R.id.itemBaithiImg);

            itemBaithiBtnBatdau.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (baithiClickListener != null) {
                baithiClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface BaithiClickListener {
        void onItemClick(View view, int position);
    }
}
