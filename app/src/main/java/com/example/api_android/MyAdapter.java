package com.example.api_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.specialtyName.setText(items.get(position).getSpecialty_name());

        holder.mathPoint.setText(String.valueOf(items.get(position).getMath_point()));
        holder.rusPoint.setText(String.valueOf(items.get(position).getRus_point()));
        holder.itkPoint.setText(String.valueOf(items.get(position).getItk_point()));
        holder.chemPoint.setText(String.valueOf(items.get(position).getChem_point()));
        holder.socPoint.setText(String.valueOf(items.get(position).getSoc_point()));
        holder.physPoint.setText(String.valueOf(items.get(position).getPhys_point()));
        holder.enPoint.setText(String.valueOf(items.get(position).getEn_point()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
