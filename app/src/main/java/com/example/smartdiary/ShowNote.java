package com.example.smartdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartdiary.model.Note;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowNote extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    NewNoteAdapter newNoteAdapter;
    ArrayList<Note> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

     recyclerView =findViewById(R.id.NoteList);
     databaseReference = FirebaseDatabase.getInstance().getReference("Notes");
     recyclerView.setHasFixedSize(true);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Note>();
        newNoteAdapter = new NewNoteAdapter(this,list);
        recyclerView.setAdapter(newNoteAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Note note = dataSnapshot.getValue(Note.class);
                    list.add(note);
                }

                newNoteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}