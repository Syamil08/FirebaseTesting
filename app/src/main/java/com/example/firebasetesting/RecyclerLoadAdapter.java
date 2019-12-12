package com.example.firebasetesting;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerLoadAdapter extends RecyclerView.Adapter<RecyclerLoadAdapter.RecyclerViewHolder> {
    public static ArrayList<RecyclerAddModel> mRecyclerModel = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_loadagenda,parent,false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        RecyclerAddModel currentItem = mRecyclerModel.get(position);

        holder.mJudul.setText(currentItem.getJudul());
        holder.mDeskripsi.setText(currentItem.getDeskripsi());

    }

    @Override
    public int getItemCount() {
        try {
            return mRecyclerModel.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public RecyclerLoadAdapter(ArrayList<RecyclerAddModel> recyclerModel){
        this.mRecyclerModel = recyclerModel;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView mJudul,mDeskripsi;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mJudul = itemView.findViewById(R.id.tv_judulLoad);
            mDeskripsi = itemView.findViewById(R.id.tv_tanggalLoad);
        }
    }

}
