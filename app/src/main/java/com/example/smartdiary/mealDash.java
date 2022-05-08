package com.example.smartdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class mealDash extends AppCompatActivity {

    TextView month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealDash);


        //display the current date and month
        month = findViewById(R.id.month);
        Date currentDate = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        month.setText(formattedDate);

        ImageButton btn_exercise = findViewById(R.id.btn_exercise);
        btn_exercise.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,Meal_list.class);
            startActivity(intent);

        });

        ImageButton btn_food = findViewById(R.id.btn_food);
        btn_food.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,create.class);
            startActivity(intent);

        });





    }

}