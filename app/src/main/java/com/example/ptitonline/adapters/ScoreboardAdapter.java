package com.example.ptitonline.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptitonline.R;
import com.example.ptitonline.models.Nguoidung;

import java.util.List;

public class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardAdapter.ScoreboardViewHolder> {
    private List<Nguoidung> list;

    public List<Nguoidung> getList() {
        return list;
    }

    public void setList(List<Nguoidung> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ScoreboardAdapter() {
    }

    @NonNull
    @Override
    public ScoreboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scoreboard, parent, false);
        return new ScoreboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreboardViewHolder holder, int position) {
        Nguoidung user = list.get(position);
        if (user == null) return;
        holder.userRank.setText(position + 1 + "");
        holder.userUsername.setText(user.getUsername());
        holder.userHoten.setText(user.getHoten());
        holder.userDiem.setText(user.getDiemluyentap() + "");
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public class ScoreboardViewHolder extends RecyclerView.ViewHolder {
        private TextView userRank, userUsername, userHoten, userDiem;

        public ScoreboardViewHolder(@NonNull View itemView) {
            super(itemView);
            userRank = itemView.findViewById(R.id.userRank);
            userUsername = itemView.findViewById(R.id.userUsername);
            userHoten = itemView.findViewById(R.id.userHoten);
            userDiem = itemView.findViewById(R.id.userDiem);
        }
    }
}
