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
    private TextInputEditText Date,NoteTopic,DailyNote ;
    private Button saveNoteBtn;
    private ProgressBar PBLoading;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String NoteID;
    DatabaseReference noteDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        NoteTopic = findViewById(R.id.idEditNoteTopic);
        DailyNote = findViewById(R.id.idEditDailyNote);
        saveNoteBtn=findViewById(R.id.idSaveNoteBtn);
        PBLoading=findViewById(R.id.idPBLoading);



        noteDbRef=FirebaseDatabase.getInstance().getReference().child("notes");

        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 insertNote();
            }
        });



    }
    private void insertNote(){
        String Topic = NoteTopic.getText().toString();
        String note=DailyNote.getText().toString();

        Notes notes= new Notes(Topic,DailyNote);
        noteDbRef.push().setValue(notes);
    }
}