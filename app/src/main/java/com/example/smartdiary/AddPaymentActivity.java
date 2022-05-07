package com.example.smartdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPaymentActivity extends AppCompatActivity {

    private TextInputEditText dateEditText,FoodPayEdit,TransEdit,OtherExpenEdit,happysnckEdit;
    private Button addExpenbtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String PayID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        dateEditText = findViewById(R.id.idEditDate);
        FoodPayEdit = findViewById(R.id.idEditAmountforFood);
        TransEdit = findViewById(R.id.idEditAmountforTransport);
        OtherExpenEdit = findViewById(R.id.idEditOthrExpenses);
        happysnckEdit = findViewById(R.id.idEdithappySnks);
        addExpenbtn = findViewById(R.id.idAddpaymentbtn);
        loadingPB = findViewById(R.id.idPBloading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Spending");


            addExpenbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadingPB.setVisibility(View.VISIBLE);
                    String Day = dateEditText.getText().toString();
                    String Food = FoodPayEdit.getText().toString();
                    String Transport = TransEdit.getText().toString();
                    String Snacks = happysnckEdit.getText().toString();
                    String Expenses = OtherExpenEdit.getText().toString();
                    PayID = Day;
                    PayRVModal payRVModal = new PayRVModal(Day,Transport,Snacks,Expenses,PayID);

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            loadingPB.setVisibility(View.GONE);
                            databaseReference.child(PayID).setValue(payRVModal);
                            Toast.makeText(AddPaymentActivity.this, "Expenses Added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddPaymentActivity.this, AllPaymentActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(AddPaymentActivity.this, "Error is" + error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });



    }
}