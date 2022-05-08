package com.example.smartdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditNoteActivity extends AppCompatActivity {
    private TextInputEditText NoteDate,NoteTopic,DailyNote ;
    private Button UpdateNoteBtn,DeleteNoteBtn;
    private ProgressBar PBLoading;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String NoteID;
    private NotesRvmodel notesRvmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        NoteDate = findViewById(R.id.idEditNoteDate);
        NoteTopic = findViewById(R.id.idEditNoteTopic);
        DailyNote = findViewById(R.id.idEditDailyNote);
        DeleteNoteBtn=findViewById(R.id.idDeleteNoteBtn);
        UpdateNoteBtn=findViewById(R.id.idUpdateNoteBtn);
        PBLoading=findViewById(R.id.idPBLoading);
        databaseReference=firebaseDatabase.getReference("notes").child(NoteID);
        notesRvmodel= getIntent().getParcelableExtra("notes");

        if (notesRvmodel!=null){

            NoteDate.setText(notesRvmodel.getNoteId());
            NoteTopic.setText(notesRvmodel.getNoteTopic());
            DailyNote.setText(notesRvmodel.getDailyNote());


        }
        databaseReference = firebaseDatabase.getReference("Spending").child(NoteID);
        UpdateNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PBLoading.setVisibility(View.VISIBLE);
                String Date = NoteDate.getText().toString();
                String Topic =NoteTopic.getText().toString();
                String DayNote =DailyNote.getText().toString();


                //passing data

                Map<String,Object> map = new HashMap<>();
                map.put("day",Date);
                map.put("food",Topic);
                map.put("transport",DayNote);



                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PBLoading.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditNoteActivity.this, "Note Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditNoteActivity.this,AllPaymentActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditNoteActivity.this, "Fail to update", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    private void DeleteNotes(){
        databaseReference.removeValue();
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditNoteActivity.this, AllPaymentActivity.class));

    }
}