package com.example.smartdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartdiary.model.Note;

import java.util.ArrayList;
import java.util.Date;

public class NewNoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
         Context context;
         ArrayList<Note> list;

    public NewNoteAdapter(Context context, ArrayList<Note> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.noteitem,parent,false);
        return new NoteAdapter.NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {

        Note note =list.get(position);
        holder.Date.setText(note.getDate());
        holder.Topic.setText(note.getNoteTopic());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView Date,Topic;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            Date= itemView.findViewById(R.id.item_date);
            Topic = itemView.findViewById(R.id.item_topic);
        }
    }
}
