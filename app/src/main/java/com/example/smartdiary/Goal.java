package com.example.mydiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton btn_goal = findViewById(R.id.GoHealth);
        btn_goal.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity2.this,MainActivity.class);
            startActivity(intent);
        });
    }
}