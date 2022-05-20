package com.example.smartdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

class create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //create edit
        Date currentDate = Calendar.getInstance().getTime();
        final String date = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        final EditText editName = findViewById(R.id.meal_name);
        final EditText protein = findViewById(R.id.meal_protein);
        final EditText fat = findViewById(R.id.meal_fat);
        final EditText carbs = findViewById(R.id.meal_carbs);
        final EditText calories = findViewById(R.id.meal_calories);
        final EditText description = findViewById(R.id.meal_description);
        Button btn = findViewById(R.id.edit_button);

        MealDAO dao = new MealDAO();
        Meal ml_edit = (Meal) getIntent().getSerializableExtra("EDIT");
        if (ml_edit!= null){
            btn.setText("UPDATE");
            editName.setText(ml_edit.getMeal_name());
            protein.setText(ml_edit.getMeal_protien());
            fat.setText(ml_edit.getMeal_fat());
            carbs.setText(ml_edit.getMeal_carbs());
            calories.setText(ml_edit.getMeal_calories());
            description.setText(ml_edit.getMeal_description());
        }
        else{
            btn.setText("SUBMIT");

        }

        btn.setOnClickListener(v->{
            Meal ml = new Meal(editName.getText().toString(),protein.getText().toString(),fat.getText().toString(),carbs.getText().toString(),calories.getText().toString(),description.getText().toString(),date);
            if(ml_edit == null) {
                dao.add(ml).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "fail" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else{
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("meal_name", editName.getText().toString());
                hashMap.put("meal_protein", protein.getText().toString());
                hashMap.put("meal_fat", fat.getText().toString());
                hashMap.put("meal_carbs", carbs.getText().toString());
                hashMap.put("meal_calories", calories.getText().toString());
                hashMap.put("meal_description", description.getText().toString());
                hashMap.put("meal_date", date);
                dao.update(ml_edit.getKey(), hashMap).addOnSuccessListener(suc->{
                    Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "fail" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }
        });
    }
}