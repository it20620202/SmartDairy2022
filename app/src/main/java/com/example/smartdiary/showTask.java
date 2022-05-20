package com.example.smartdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.smartdiary.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showTask extends AppCompatActivity {
    private FloatingActionButton addBtn;
    private RecyclerView recyclerView;
    private todoAdapter todoAdapter;
    private ArrayList<Task> list;
    private DatabaseReference databaseReference;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        dialog = new ProgressDialog(showTask.this);
        dialog.setMessage("loading...");
        dialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("Todo");

        addBtn = findViewById(R.id.addbtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask = new Intent(showTask.this, addTask.class);
                startActivity(addTask);
            }
        });

        buildRecyclerView();

        readData();

        todoAdapter.setOnItemClickListener(new todoAdapter.OnItemClickListener() {
            @Override
            public void onDelete(String todoKey, String taskName, int position) {

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseReference newRef = databaseReference.child(todoKey);
                        newRef.removeValue();
                        list.remove(position);
                        todoAdapter.notifyItemRemoved(position);
                        finish();
                        overridePendingTransition(0, 0);
                        Toast.makeText(showTask.this,"Task completed!", Toast.LENGTH_LONG).show();
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        finish();
                    }
                }, 700);

            }

            @Override
            public void onUpdate(String todoKey, String taskName, String description, int position) {
                Intent updateTask = new Intent(showTask.this,updateTask.class);
                updateTask.putExtra("todoKey",todoKey);
                updateTask.putExtra("taskName", taskName);
                updateTask.putExtra("description", description);
                startActivity(updateTask);
            }
        });



    }

    private void readData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Task todo = dataSnapshot.getValue(Task.class);
                    todo.setId(dataSnapshot.getKey());

                    list.add(todo);
                }

                todoAdapter.notifyDataSetChanged();
                dialog.hide();
                dialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.todolist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        todoAdapter = new todoAdapter(this,list);
        recyclerView.setAdapter(todoAdapter);
    }
}

