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

import com.example.smartdiary.model.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
    private Note note ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Notes");
        note = new Note();
        saveNoteBtn=findViewById(R.id.idSaveNoteBtn);


        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Date=findViewById(R.id.idEditNoteDate);
             NoteTopic = findViewById(R.id.idEditNoteTopic);
             DailyNote = findViewById(R.id.idEditDailyNote);
             PBLoading=findViewById(R.id.idPBLoading);


          String Date_txt=Date.getText().toString();
          String NoteTopic_txt = NoteTopic.getText().toString();
          String DailyNote_txt = DailyNote.getText().toString();

          //validations
             if (TextUtils.isEmpty(Date_txt)){
                 Toast.makeText(AddNote.this,"Please enter date", Toast.LENGTH_LONG).show();
             }
             else if(TextUtils.isEmpty(NoteTopic_txt)){
                 Toast.makeText(AddNote.this,"Please enter topic", Toast.LENGTH_LONG).show();
             }
             else if(TextUtils.isEmpty(DailyNote_txt)){
                 Toast.makeText(AddNote.this,"Please enter note", Toast.LENGTH_LONG).show();
             }
             else{
                 insertData(Date_txt,NoteTopic_txt,DailyNote_txt);
             }



         }
     });
    }
    private void insertData(String Date_txt,String NoteTopic_txt,String DailyNote_txt){
            note.setDate(Date_txt);
            note.setNoteTopic(NoteTopic_txt);
            note.setDailyNote(DailyNote_txt);


            DatabaseReference postRef = databaseReference.push();

            postRef.setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> note) {
                    if (note.isSuccessful()){
                        Toast.makeText(AddNote.this, "Note added", Toast.LENGTH_SHORT).show();

                        Intent AllNote = new Intent(AddNote.this,showTask.class);
                        startActivity(AllNote);
                        finish();
                    }
                    else{
                        Toast.makeText(AddNote.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

}