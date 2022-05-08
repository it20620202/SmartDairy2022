package com.example.smartdiary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MealVH extends RecyclerView.ViewHolder{
    public TextView txt_MLname,txt_protein,txt_fat,txt_carbs,txt_calories,txt_descript,txt_date,txt_option;
    public MealVH(@NonNull View itemView) {
        super(itemView);
        txt_MLname = itemView.findViewById(R.id.txt_MLname);
        txt_protein = itemView.findViewById(R.id.txt_protein);
        txt_fat = itemView.findViewById(R.id.txt_fat);
        txt_carbs = itemView.findViewById(R.id.txt_carbs);
        txt_calories = itemView.findViewById(R.id.txt_calories);
        txt_descript = itemView.findViewById(R.id.txt_description);
        txt_date= itemView.findViewById(R.id.txt_date);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
