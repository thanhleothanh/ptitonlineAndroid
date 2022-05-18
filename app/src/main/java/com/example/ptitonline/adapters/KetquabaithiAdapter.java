package com.example.ptitonline.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptitonline.R;
import com.example.ptitonline.models.Ketquabaithi;

import java.util.List;

public class KetquabaithiAdapter extends RecyclerView.Adapter<KetquabaithiAdapter.KetquabaithiViewHolder> {
    private List<Ketquabaithi> list;

    public void setList(List<Ketquabaithi> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KetquabaithiAdapter.KetquabaithiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ketquabaithi, parent, false);
        return new KetquabaithiAdapter.KetquabaithiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KetquabaithiAdapter.KetquabaithiViewHolder holder, int position) {
        Ketquabaithi ketquabaithi = list.get(position);
        if (ketquabaithi == null) return;
        holder.itemKetquabaithiTenbaithi.setText("Bài thi: " + ketquabaithi.getBaithi().getTenbaithi());
        holder.itemKetquabaithiGiaovien.setText("Giáo viên: " + ketquabaithi.getBaithi().getGiaovien().getHoten());
        holder.itemKetquabaithiMonhoc.setText("Môn học: " + ketquabaithi.getBaithi().getMonhoc().getTenmon());
        holder.itemKetquabaithiMota.setText("Mô tả: " + ketquabaithi.getBaithi().getMota());
        holder.itemKetquabaithiThoigianlambai.setText("Thời gian làm bài: " + ketquabaithi.getThoigianlambai());
        holder.itemKetquabaithiDiem.setText("Điểm: " + ketquabaithi.getDiem() + "");
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public class KetquabaithiViewHolder extends RecyclerView.ViewHolder {
        private TextView itemKetquabaithiTenbaithi,
                itemKetquabaithiGiaovien,
                itemKetquabaithiMota,
                itemKetquabaithiMonhoc,
                itemKetquabaithiThoigianlambai,
                itemKetquabaithiDiem;

        public KetquabaithiViewHolder(@NonNull View itemView) {
            super(itemView);
            itemKetquabaithiTenbaithi = itemView.findViewById(R.id.itemKetquabaithiTenbaithi);
            itemKetquabaithiGiaovien = itemView.findViewById(R.id.itemKetquabaithiGiaovien);
            itemKetquabaithiMota = itemView.findViewById(R.id.itemKetquabaithiMota);
            itemKetquabaithiMonhoc = itemView.findViewById(R.id.itemKetquabaithiMonhoc);
            itemKetquabaithiThoigianlambai = itemView.findViewById(R.id.itemKetquabaithiThoigianlambai);
            itemKetquabaithiDiem = itemView.findViewById(R.id.itemKetquabaithiDiem);
        }
    }
}
