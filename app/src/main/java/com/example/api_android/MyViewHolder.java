package com.example.api_android;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView specialtyName, mathPoint, rusPoint, itkPoint, chemPoint, socPoint, physPoint, enPoint;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        specialtyName = itemView.findViewById(R.id.specialtyName);

        mathPoint = itemView.findViewById(R.id.mathPoint);
        rusPoint = itemView.findViewById(R.id.rusPoint);
        itkPoint = itemView.findViewById(R.id.itkPoint);
        chemPoint = itemView.findViewById(R.id.chemPoint);
        socPoint = itemView.findViewById(R.id.socPoint);
        physPoint = itemView.findViewById(R.id.physPoint);
        enPoint = itemView.findViewById(R.id.enPoint);
    }
}
