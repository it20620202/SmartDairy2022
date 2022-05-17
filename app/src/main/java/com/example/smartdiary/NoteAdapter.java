package com.example.smartdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdiary.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {


    Context context;
    ArrayList<Note> list;
    private static todoAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onDelete(String Date,String Topic,int position);
        void onUpdate(String Date,String Topic, String DailyNote,int position);
    }

    public NoteAdapter(Context context, ArrayList<Note> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.noteitem, parent, false);
        return new NoteViewHolder(v, mListener);
    }
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        Note note = list.get(position);
        holder.Date.setText(note.getDate());
        holder.Topic.setText(note.getNoteTopic());
        holder.DailyNote.setText(note.getDailyNote());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView Date, Topic, DailyNote;
        ImageView editBtn;
        CheckBox completed;

        public NoteViewHolder(@NonNull View itemView, final todoAdapter.OnItemClickListener listener) {
            super(itemView);


            Date =itemView.findViewById(R.id.item_date);
            Topic =itemView.findViewById(R.id.item_topic);
            editBtn =itemView.findViewById(R.id.editNote);
            completed=itemView.findViewById(R.id.completed);


            completed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDelete(Date.getText().toString(), Topic.getText().toString(), position);
                        }

                    }

                }
            });
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            String Date_txt = Date.getText().toString();
                            String Topic_txt = Topic.getText().toString();
                            String DailyNote_txt = DailyNote.getText().toString();

                            listener.onUpdate(Date_txt, Topic_txt, DailyNote_txt,position);
                        }

                    }

                }
            });

        }

        public NoteViewHolder(View view) {
            super(view);
        }
    }


}
