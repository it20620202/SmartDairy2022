package com.example.smartdiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllPaymentActivity extends AppCompatActivity implements PayRvAdapter.PayClickInterface {

    private RecyclerView PaymentsRV;
    private ProgressBar PBloading;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<PayRVModal> PayRVModalArrayList;
    private RelativeLayout bottomsheetRL;
    private PayRvAdapter payRvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PBloading = findViewById(R.id.idPBloading);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Spending");
        PayRVModalArrayList = new ArrayList<>();
        bottomsheetRL = findViewById(R.id.idRLBsheet);
        payRvAdapter = new PayRvAdapter(PayRVModalArrayList,this,this);
        PaymentsRV.setLayoutManager(new LinearLayoutManager(this));
        PaymentsRV.setAdapter(payRvAdapter);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllPaymentActivity.this,AddPaymentActivity.class));
            }
        });

        getAllPayments();


    }

    private void getAllPayments(){

        PayRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PBloading.setVisibility(View.GONE);
                PayRVModalArrayList.add(snapshot.getValue(PayRVModal.class));
                payRvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PBloading.setVisibility(View.GONE);
                payRvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                PBloading.setVisibility(View.GONE);
                payRvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                PBloading.setVisibility(View.GONE);
                payRvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onPayClick(int position) {

        displayBottomSheet(PayRVModalArrayList.get(position));

    }

    private void displayBottomSheet(PayRVModal payRVModal){


    }
}