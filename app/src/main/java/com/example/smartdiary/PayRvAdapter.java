package com.example.smartdiary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PayRvAdapter extends RecyclerView.Adapter<PayRvAdapter.ViewHolder> {
    private ArrayList<PayRVModal> payRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private PayClickInterface payClickInterface;

    public PayRvAdapter(ArrayList<PayRVModal> payRVModalArrayList, Context context, PayClickInterface payClickInterface) {
        this.payRVModalArrayList = payRVModalArrayList;
        this.context = context;
        this.payClickInterface = payClickInterface;
    }

    @NonNull
    @Override
    public PayRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pay_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayRvAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PayRVModal payRVModal = payRVModalArrayList.get(position);
        holder.dateTv.setText(payRVModal.getDay());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        payClickInterface.onPayClick(position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return payRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView dateTv;
    private ImageView payIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.idTVPay);
            payIV = itemView.findViewById(R.id.idIVpay);
        }
    }

    public interface PayClickInterface{
        void onPayClick(int position);
    }

}
