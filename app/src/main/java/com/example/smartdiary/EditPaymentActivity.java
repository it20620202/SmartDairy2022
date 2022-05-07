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

import java.util.HashMap;
import java.util.Map;

public class EditPaymentActivity extends AppCompatActivity {

    private TextInputEditText dateEditText,FoodPayEdit,TransEdit,OtherExpenEdit,happysnckEdit;
    private Button updateExpenbtn,deleteExpenbtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String PayID;
    private PayRVModal payRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dateEditText = findViewById(R.id.idEditDate);
        FoodPayEdit = findViewById(R.id.idEditAmountforFood);
        TransEdit = findViewById(R.id.idEditAmountforTransport);
        OtherExpenEdit = findViewById(R.id.idEditOthrExpenses);
        happysnckEdit = findViewById(R.id.idEdithappySnks);
        updateExpenbtn = findViewById(R.id.idAddpaymentbtn);
        deleteExpenbtn = findViewById(R.id.idDeletepaymentbtn);
        loadingPB = findViewById(R.id.idPBloading);
         payRVModal = getIntent().getParcelableExtra("Spending");

         if (payRVModal!=null){

             dateEditText.setText(payRVModal.getDay());
             FoodPayEdit.setText(payRVModal.getFood());
             TransEdit.setText(payRVModal.getTransport());
             OtherExpenEdit.setText(payRVModal.getExpenses());
             happysnckEdit.setText(payRVModal.getSnacks());
             PayID = payRVModal.getPayID();

         }

        databaseReference = firebaseDatabase.getReference("Spending").child(PayID);
         updateExpenbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 loadingPB.setVisibility(View.VISIBLE);
                 String Day = dateEditText.getText().toString();
                 String Food = FoodPayEdit.getText().toString();
                 String Transport = TransEdit.getText().toString();
                 String Snacks = happysnckEdit.getText().toString();
                 String Expenses = OtherExpenEdit.getText().toString();

                 //passing data

                 Map<String,Object>map = new HashMap<>();
                 map.put("day",Day);
                 map.put("food",Food);
                 map.put("transport",Transport);
                 map.put("snacks",Snacks);
                 map.put("expenses",Expenses);
                 map.put("PayID",PayID);


                 databaseReference.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         loadingPB.setVisibility(View.GONE);
                         databaseReference.updateChildren(map);
                         Toast.makeText(EditPaymentActivity.this, "Expenses Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditPaymentActivity.this,AllPaymentActivity.class));
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {
                         Toast.makeText(EditPaymentActivity.this, "Fail to update", Toast.LENGTH_SHORT).show();
                     }
                 });
             }
         });

         deleteExpenbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 deleteExpenses();

             }
         });
    }

  private void deleteExpenses(){
        databaseReference.removeValue();
      Toast.makeText(this, "Expenses Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditPaymentActivity.this, AllPaymentActivity.class));

  }
}