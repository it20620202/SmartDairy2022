package com.example.smartdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

public class AddNote extends AppCompatActivity {
    private TextInputEditText NoteTopic,DailyNote ;
    private Button saveNoteBtn;
    private ProgressBar PBLoading;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String NoteID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        NoteTopic = findViewById(R.id.idEditNoteTopic);
        DailyNote = findViewById(R.id.idEditDailyNote);
        firebaseDatabase =FirebaseDatabase.getInstance();
        saveNoteBtn=findViewById(R.id.idSaveNoteBtn);
        PBLoading=findViewById(R.id.idPBLoading);
        databaseReference= firebaseDatabase.getReference("notes");


        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Topic =NoteTopic.getText().toString();
                String note = DailyNote.getText().toString();
                NoteID=Topic;

                NotesRvmodel notesRvmodel= new NotesRvmodel(NoteTopic,DailyNote);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(NoteID).setValue(notesRvmodel);
                        Toast.makeText(AddNote.this, "Note Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddNote.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddNote.this, "Error is occured ", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
    }
}