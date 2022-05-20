package com.example.mydiaryapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GoalVH extends RecyclerView.ViewHolder{

        //list TextViews
        public TextView txt_dateG,txt_stepsGo,txt_calsGo,txt_motiGo,txt_achieveGo,txt_option;

        //initialize
        public GoalVH(@NonNull View itemView){
                super(itemView);
                txt_dateG=itemView.findViewById(R.id.txt_dateG);
                txt_stepsGo=itemView.findViewById(R.id.txt_Gsteps);
                txt_calsGo=itemView.findViewById(R.id.txt_Gcals);
                txt_motiGo=itemView.findViewById(R.id.txt_motivationG);
                txt_achieveGo=itemView.findViewById(R.id.txt_achieveG);
                txt_option=itemView.findViewById(R.id.txt_option);
        }
}