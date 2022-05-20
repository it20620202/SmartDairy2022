package com.example.smartdairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smartdairy.model.Notes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateNote extends AppCompatActivity {
    private TextInputEditText Date,NoteTopic,DailyNote ;
    private Button saveNoteBtn;
    private ProgressBar PBLoading;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Notes note ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);


        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Notes");
        note = new Notes();


        saveNoteBtn=findViewById(R.id.idSaveNoteBtn);
        Date=findViewById(R.id.idEditNoteDate);
        NoteTopic = findViewById(R.id.idEditNoteTopic);

        String noteKey=getIntent().getStringExtra("noteKey");
        String DayExtra=getIntent().getStringExtra("Date");
        String TopicExtra=getIntent().getStringExtra("Topic");

        Date.setText(DayExtra);
        NoteTopic.setText(TopicExtra);


        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Date_txt=Date.getText().toString();
                String NoteTopic_txt = NoteTopic.getText().toString();


                //validations
                if (TextUtils.isEmpty(Date_txt)){
                    Toast.makeText(UpdateNote.this,"Please enter date", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(NoteTopic_txt)){
                    Toast.makeText(UpdateNote.this,"Please enter topic", Toast.LENGTH_LONG).show();
                }

                else{
                    updateData(Date_txt,NoteTopic_txt);
                }

            }

            private void updateData(String date_txt, String noteTopic_txt) {
                note.setDate(date_txt);
                note.setNoteTopic(noteTopic_txt);

                DatabaseReference postRef = databaseReference.child(noteKey);

                postRef.setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UpdateNote.this, "Note updated", Toast.LENGTH_SHORT).show();
                            Intent showTask = new Intent(UpdateNote.this,ShowNote.class);
                            startActivity(showTask);
                            finish();
                        }
                        else{
                            Toast.makeText(UpdateNote.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });









    }
}