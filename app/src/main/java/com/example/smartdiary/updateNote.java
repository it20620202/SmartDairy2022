package com.example.smartdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartdiary.model.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateNote extends AppCompatActivity {
    private FloatingActionButton saveBtn;
    private EditText taskName, description;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Task todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Todo");
        todo = new Task();

        taskName = findViewById(R.id.taskName);
        description = findViewById(R.id.taskDescription);

        String todoKey = getIntent().getStringExtra("todoKey");
        String taskNameExtra = getIntent().getStringExtra("taskName");
        String descriptionExtra = getIntent().getStringExtra("description");

        taskName.setText(taskNameExtra);
        description.setText(descriptionExtra);

        saveBtn = findViewById(R.id.savebtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName_txt = taskName.getText().toString();
                String description_txt = description.getText().toString();

                if (TextUtils.isEmpty(taskName_txt)){
                    Toast.makeText(updateNote.this,"Please enter task", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(description_txt)){
                    Toast.makeText(updateNote.this,"Please enter description", Toast.LENGTH_LONG).show();
                }
                else{
                    updateData(todoKey, taskName_txt, description_txt);
                }
            }
        });
    }

    private void updateData(String todoKey, String taskName_txt, String description_txt) {
        todo.setTask(taskName_txt);
        todo.setDescription(description_txt);

        DatabaseReference postRef = databaseReference.child(todoKey);

        postRef.setValue(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(updateTask.this, "Task updated", Toast.LENGTH_SHORT).show();
                    Intent showTask = new Intent(updateTask.this,showTask.class);
                    startActivity(showTask);
                    finish();
                }
                else{
                    Toast.makeText(updateTask.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
