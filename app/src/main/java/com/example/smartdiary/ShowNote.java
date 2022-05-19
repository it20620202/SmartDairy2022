package com.example.smartdairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.example.smartdairy.model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowNote extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    NoteAdapter noteAdapter;
    ArrayList<Notes> list;


    private FloatingActionButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_show_note);

            recyclerView =findViewById(R.id.NoteList);
            databaseReference = FirebaseDatabase.getInstance().getReference("Notes");
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            list =new ArrayList<>();
            noteAdapter =new NoteAdapter(this,list);
            recyclerView.setAdapter(noteAdapter);

            addBtn = findViewById(R.id.addbtn);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent addTask = new Intent(ShowNote.this, AddNote.class);
                    startActivity(addTask);
                }
            });

        databaseReference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {for (DataSnapshot dataSnapshot: snapshot.getChildren() ){

                        Notes notes = dataSnapshot.getValue(Notes.class);
                        notes.setId(dataSnapshot.getKey());
                        list.add(notes);

                    }

                    noteAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        noteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onDelete(String noteKey, String Topic, int position) {

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseReference newRef = databaseReference.child(noteKey);
                        newRef.removeValue();
                        list.remove(position);
                        noteAdapter.notifyItemRemoved(position);
                        finish();
                        overridePendingTransition(0, 0);
                        Toast.makeText(ShowNote.this,"Note Deleted!", Toast.LENGTH_LONG).show();
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        finish();
                    }
                }, 700);

            }

            @Override
            public void onUpdate(String noteKey,String Date, String Topic, int position) {


            }

        });


    }
}