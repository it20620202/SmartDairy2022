package com.example.smartdiary;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smartdiary.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AllNotes extends AppCompatActivity {
    private ProgressBar PBLoading;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FloatingActionButton addBtn;
    private RecyclerView recyclerView;
    private todoAdapter todoAdapter;
    private ArrayList<Note> list;
    private ProgressDialog dialog;
    private Object NoteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

        dialog = new ProgressDialog(AllNotes.this);
        dialog.setMessage("loading...");
        dialog.show();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Notes");


        addBtn = findViewById(R.id.addbtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddNote = new Intent(AllNotes.this, AddNote.class);
                startActivity(AddNote);
            }
        });
        buildRecyclerView();
        readData();



        NoteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onDelete(String Date, String Topic, int position) {

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseReference newRef = databaseReference.child(Date);
                        newRef.removeValue();
                        list.remove(position);
                        todoAdapter.notifyItemRemoved(position);
                        finish();
                        overridePendingTransition(0, 0);
                        Toast.makeText(AllNotes.this,"Task completed!", Toast.LENGTH_LONG).show();
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        finish();
                    }
                }, 700);

            }
            @Override
            public void onUpdate(String Date, String Topic, String DailyNote, int position) {
                Intent updateNote = new Intent(AllNotes.this,updateNote.class);
               
                startActivity(updateNote);
            }
        });

    }
    private void readData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Note note = dataSnapshot.getValue(Note.class);
                    note.setDate(dataSnapshot.getKey());

                    list.add(note);
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
        recyclerView = findViewById(R.id.NoteList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Note>();
        NoteAdapter = new NoteAdapter(this,list);
        recyclerView.setAdapter(todoAdapter);
    }

}